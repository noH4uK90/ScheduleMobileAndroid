package com.example.schedulemobile.domain.models

data class Pair(
    val id: Int,
    val number: Int,
    val changed: Boolean,
    val timetableId: Int,
    val time: PairTime?,
    val discipline: Discipline?,
    val teacherClassrooms: List<TeacherClassroom>,
)