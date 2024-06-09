package com.example.schedulemobileandroid.presentation.viewModels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DateWheelViewModel @Inject constructor() : ViewModel(), DefaultLifecycleObserver {
    private val _currentDate = MutableStateFlow(LocalDate.now())
    val currentDate: StateFlow<LocalDate> = _currentDate

    private val _weekSlider = MutableStateFlow(generateInitialWeeks())
    val weekSlider: StateFlow<List<List<LocalDate>>> = _weekSlider

    private val _currentWeekIndex = MutableStateFlow(1)
    val currentWeekIndex: StateFlow<Int> = _currentWeekIndex

    init {
        viewModelScope.launch {
            // Initial setup if needed
        }
    }

    fun updateCurrentDate(date: LocalDate) {
        _currentDate.value = date
    }

    fun updateCurrentWeekIndex(index: Int) {
        viewModelScope.launch {
            if (index == 0) {
                addPreviousWeek()
                _currentWeekIndex.value = 1 // Shift index to the right after adding a week at the start
            } else if (index == _weekSlider.value.size - 1) {
                addNextWeek()
            }
            _currentWeekIndex.value = index
        }
    }

    private fun addPreviousWeek() {
        val firstWeek = _weekSlider.value.first()
        val newWeek = getPreviousWeek(firstWeek.first())
        _weekSlider.value = listOf(newWeek) + _weekSlider.value
    }

    private fun addNextWeek() {
        val lastWeek = _weekSlider.value.last()
        val newWeek = getNextWeek(lastWeek.last())
        _weekSlider.value = _weekSlider.value + listOf(newWeek)
    }

    private fun generateInitialWeeks(): List<List<LocalDate>> {
        val currentWeek = getCurrentWeek()
        val previousWeek = getPreviousWeek(currentWeek.first())
        val nextWeek = getNextWeek(currentWeek.last())
        return listOf(previousWeek, currentWeek, nextWeek)
    }

    private fun getCurrentWeek(): List<LocalDate> {
        val today = LocalDate.now()
        val startOfWeek = today.minusDays(today.dayOfWeek.value.toLong() - 1)
        return (0..6).map { startOfWeek.plusDays(it.toLong()) }
    }

    private fun getPreviousWeek(startDate: LocalDate): List<LocalDate> {
        return (0..6).map { startDate.minusDays(7 - it.toLong()) }
    }

    private fun getNextWeek(endDate: LocalDate): List<LocalDate> {
        return (0..6).map { endDate.plusDays(it.toLong() + 1) }
    }
}