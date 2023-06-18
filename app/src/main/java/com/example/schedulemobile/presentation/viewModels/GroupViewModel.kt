package com.example.schedulemobile.presentation.viewModels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.schedulemobile.data.remote.GroupSource
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.repository.GroupRepository
import com.google.gson.Gson
import com.squareup.moshi.Json
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.http.Query
import java.util.Locale
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GroupViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    val repository: GroupRepository
): ViewModel(), DefaultLifecycleObserver {

    var isSnackBarShowing: Boolean by mutableStateOf(false)
    private val _searchQuery = MutableStateFlow("")
    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    var groups = _searchQuery
        .flatMapLatest { query ->
            Pager(PagingConfig(pageSize = 20)) {
                GroupSource(repository, query)
            }.flow
        }
        .cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    suspend fun validGroup(groupName: String): Boolean {
        val groupByName = repository.searchGroup(search = groupName).data?.items?.first()
        return if (groupByName?.name?.lowercase(Locale.getDefault()) == groupName.lowercase(Locale.getDefault())) {
            saveGroup(groupByName)
            true
        }
        else {
            isSnackBarShowing = true
            false
        }
    }

    private fun saveGroup(group: Group) {
        val jsonGroup = Gson().toJson(group)
        sharedPreferences.edit().putString("group", jsonGroup).apply()
    }

    fun getSavedGroup(): Group? {
        val jsonGroup = sharedPreferences.getString("group", null)
        return Gson().fromJson(jsonGroup, Group::class.java)
    }

    fun clearSavedGroup() {
        sharedPreferences.edit().putString("group", null).apply()
    }
}
