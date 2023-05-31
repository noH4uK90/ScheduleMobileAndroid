package com.example.schedulemobile.presentation.viewModels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.domain.repository.CurrentTimetableRepository
import com.example.schedulemobile.domain.util.Resource
import com.example.schedulemobile.presentation.CurrentTimetableState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: CurrentTimetableRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel(), DefaultLifecycleObserver {

    var state by mutableStateOf(CurrentTimetableState())
        private set

    override fun onResume(owner: LifecycleOwner) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            try {
                when (val result = repository.getCurrentTimetableList(getCurrentGroup().id)) {
                    is Resource.Success -> {
                        state = state.copy(
                            currentTimetableList = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            currentTimetableList = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    currentTimetableList = null,
                    isLoading = false,
                    error = "Ошибка при загрузке данных"
                )
            }
        }
    }

    fun getCurrentGroup(): NetworkGroup {
        val jsonGroup = sharedPreferences.getString("group", null)
        return Gson().fromJson(jsonGroup, NetworkGroup::class.java)
    }
}