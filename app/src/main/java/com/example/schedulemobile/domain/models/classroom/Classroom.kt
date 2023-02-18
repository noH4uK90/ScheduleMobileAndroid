package com.example.schedulemobile.domain.models.classroom

import com.example.schedulemobile.domain.models.classroomType.ClassroomType

data class Classroom(
    val id: Int,
    val cabinet: String,
    val types: List<ClassroomType>
)