package com.example.schedulemobile.domain.models.classroomType

data class ClassroomTypeList(
    val items: List<ClassroomType>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)
