package com.example.schedulemobile.domain.models.currentTimetable

import com.example.schedulemobile.domain.models.day.Day
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.groupedTimetable.GroupedTimetable

data class CurrentTimetable(
    val group: Group,
    val days: List<GroupedTimetable<Day>>
)
