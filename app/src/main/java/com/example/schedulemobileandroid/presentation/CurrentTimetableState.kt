package com.example.schedulemobileandroid.presentation

import com.example.schedulemobileandroid.models.PagedList
import com.example.schedulemobileandroid.models.Timetable

data class CurrentTimetableState(
    val currentTimetableList: PagedList<Timetable>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
