package com.example.schedulemobile.domain.models.timetable

import com.example.schedulemobile.domain.models.Day
import com.example.schedulemobile.domain.models.group.Group
import com.example.schedulemobile.domain.models.Pair
import com.example.schedulemobile.domain.models.TimetableType
import com.example.schedulemobile.domain.models.WeekType

data class Timetable(
    val id: Int,
    val day: Day,
    val group: Group,
    val weekType: WeekType,
    val type: TimetableType,
    val pairs: List<Pair>
)