package com.example.schedulemobile.presentation.viewModels

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.domain.repository.GroupRepository
import com.example.schedulemobile.domain.util.Resource
import com.example.schedulemobile.presentation.GroupState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    val repository: GroupRepository
): ViewModel(), DefaultLifecycleObserver {

    var state by mutableStateOf(GroupState())
        private set

    private var currentPageNumber = 1;

    var isSnackBarShowing: Boolean by mutableStateOf(false)

    override fun onResume(owner: LifecycleOwner) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getGroups()
                }
                Log.wtf("result", result.data.toString())
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            groupList = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            groupList = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    groupList = null,
                    isLoading = false,
                    error = "Ошибка при загрузке данных"
                )
            }
        }
    }

    fun loadMoreGroups() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.loadMoreGroups(currentPageNumber + 1)
                }
                Log.wtf("result", result.data.toString())
                when (result) {
                    is Resource.Success -> {
                        currentPageNumber++
                        state = state.copy(
                            groupList = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            groupList = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    groupList = null,
                    isLoading = false,
                    error = "Ошибка при загрузке данных"
                )
            }
        }
    }

    fun validGroup(groupName: String): Boolean {
        val groupNames = state.groupList?.items?.any { it.name == groupName }
        return if (groupNames == true) {
            saveGroup(groupName)
            true
        }
        else {
            isSnackBarShowing = true
            false
        }
    }

    private fun saveGroup(groupName: String) {
        val group = state.groupList?.items?.find { it.name == groupName }
        val jsonGroup = Gson().toJson(group)
        sharedPreferences.edit().putString("group", jsonGroup).apply()
    }

    fun getSavedGroup(): NetworkGroup? {
        val jsonGroup = sharedPreferences.getString("group", null)
        return Gson().fromJson(jsonGroup, NetworkGroup::class.java)
    }

    fun clearSavedGroup() {
        sharedPreferences.edit().putString("group", null).apply()
    }
}
