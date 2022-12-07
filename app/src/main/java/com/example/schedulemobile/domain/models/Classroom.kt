package com.example.schedulemobile.domain.models

data class Classroom(
    val id: Int,
    val cabinet: String,
    val types: List<ClassroomType>
)