package com.example.schedulemobile.presentation.viewModels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.repository.GroupRepository
import com.example.schedulemobile.domain.util.Resource
import com.example.schedulemobile.presentation.GroupState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    val repository: GroupRepository
): ViewModel(), DefaultLifecycleObserver {

    var state by mutableStateOf(GroupState())
        private set

    var isSnackBarShowing: Boolean by mutableStateOf(false)

    override fun onResume(owner: LifecycleOwner) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getGroups()) {
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

    fun getSavedGroup(): Group? {
        val jsonGroup = sharedPreferences.getString("group", null)
        return Gson().fromJson(jsonGroup, Group::class.java)
    }

    fun clearSavedGroup() {
        sharedPreferences.edit().putString("group", null).apply()
    }
}