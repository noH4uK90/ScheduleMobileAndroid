package com.example.schedulemobile.domain.models.pair

import com.example.schedulemobile.domain.models.pairTime.PairTime
import com.example.schedulemobile.domain.models.teacherClassroom.TeacherClassroom
import com.example.schedulemobile.domain.models.discipline.Discipline

data class Pair(
    val id: Int,
    val number: Int,
    val changed: Boolean,
    val timetableId: Int,
    val time: PairTime?,
    val discipline: Discipline?,
    val teacherClassrooms: List<TeacherClassroom>,
)