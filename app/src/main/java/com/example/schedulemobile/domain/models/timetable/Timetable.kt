package com.example.schedulemobile.domain.models.timetable

import com.example.schedulemobile.domain.models.day.Day
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.pair.Pair
import com.example.schedulemobile.domain.models.timetableType.TimetableType
import com.example.schedulemobile.domain.models.weekType.WeekType

data class Timetable(
    val id: Int,
    val day: Day,
    val group: Group,
    val weekType: WeekType,
    val type: TimetableType,
    val pairs: List<Pair>
)