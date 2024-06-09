package com.example.schedulemobileandroid.models

data class Homework(
    val id: Int,
    val title: String,
    val description: String?,
    val group: GroupModel,
    val teacher: Teacher,
    val discipline: Discipline,
    val created: String,
    val expires: String?
)

data class CreateHomeworkCommand(
    val title: String,
    val description: String?,
    val teacherId: Int,
    val groupId: Int,
    val disciplineId: Int,
    val expires: String?
)
