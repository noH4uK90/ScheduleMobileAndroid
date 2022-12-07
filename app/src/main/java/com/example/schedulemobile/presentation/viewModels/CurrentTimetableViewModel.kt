package com.example.schedulemobile.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobile.domain.repository.CurrentTimetableRepository
import com.example.schedulemobile.presentation.CurrentTimetableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentTimetableViewModel @Inject constructor(
    private val repository: CurrentTimetableRepository,
): ViewModel() {

    var state by mutableStateOf(CurrentTimetableState())
        private set

    fun loadCurrentTimetable() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
        }
    }
}