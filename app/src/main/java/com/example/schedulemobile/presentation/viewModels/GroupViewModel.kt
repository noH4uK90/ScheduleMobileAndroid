package com.example.schedulemobile.presentation.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.repository.GroupRepository
import com.example.schedulemobile.domain.util.Resource
import com.example.schedulemobile.presentation.GroupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    val repository: GroupRepository
): ViewModel() {

    var state by mutableStateOf(GroupState())
        private set

    fun loadGroups() {
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
}