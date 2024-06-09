package com.example.schedulemobileandroid.models

data class Timetable(
    val id: Int,
    val date: String,
    val group: GroupModel,
    val day: DayModel,
    val lessons: List<Lesson>
)