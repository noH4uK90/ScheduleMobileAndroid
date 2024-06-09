package com.example.schedulemobileandroid.presentation.viewModels.scheduleViewModels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.GroupApiService
import com.example.schedulemobileandroid.models.GroupModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SelectGroupViewModel @Inject constructor(
    private val groupNetworkService: GroupApiService
): ViewModel(), DefaultLifecycleObserver {
    private val _groups = MutableStateFlow<List<GroupModel>>(emptyList())
    val groups: StateFlow<List<GroupModel>> = _groups

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        fetchGroups()
        viewModelScope.launch {
            _search
                .debounce(500)
                .distinctUntilChanged()
                .collect {
                    _groups.value = emptyList()
                    fetchGroups()
                }
        }
    }

    fun updateSearch(newSearch: String) {
        _search.value = newSearch
    }

    fun fetchGroups() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val groups = groupNetworkService.getGroups(_search.value)
                _groups.value = groups.data ?: emptyList()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}