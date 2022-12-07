package com.example.schedulemobile.domain.models.groupedTimetable

import com.example.schedulemobile.domain.models.timetable.Timetable

data class GroupedTimetable<T>(
    val key: T,
    val items: List<Timetable>
)
