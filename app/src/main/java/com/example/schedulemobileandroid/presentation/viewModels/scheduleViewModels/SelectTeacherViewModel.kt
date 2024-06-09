package com.example.schedulemobileandroid.presentation.viewModels.scheduleViewModels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.TeacherApiService
import com.example.schedulemobileandroid.models.GroupModel
import com.example.schedulemobileandroid.models.TeacherFullName
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
class SelectTeacherViewModel @Inject constructor(
    private val teacherNetworkService: TeacherApiService
): ViewModel(), DefaultLifecycleObserver {
    private val _teachers = MutableStateFlow<List<TeacherFullName>>(emptyList())
    val teachers: StateFlow<List<TeacherFullName>> = _teachers

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        fetchTeachers()
        viewModelScope.launch {
            _search
                .debounce(500)
                .distinctUntilChanged()
                .collect {
                    _teachers.value = emptyList()
                    fetchTeachers()
                }
        }
    }

    fun updateSearch(newSearch: String) {
        _search.value = newSearch
    }

    fun fetchTeachers() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val groups = teacherNetworkService.getTeacherFullNames(_search.value)
                _teachers.value = groups.data ?: emptyList()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}