package com.example.schedulemobileandroid.models

data class Lesson(
    val id: Int,
    val number: Int,
    val discipline: Discipline,
    val teacher: TeacherFullName?,
    val classroom: Classroom?,
    val type: LessonType,
    val subLesson: SubLesson?,
    val timeStart: String,
    val timeEnd: String
)

