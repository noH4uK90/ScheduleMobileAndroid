package com.example.schedulemobileandroid.models

data class SubLesson(
    val id: Int,
    val discipline: Discipline,
    val teacher: TeacherFullName?,
    val classroom: Classroom?,
    val type: LessonType
)