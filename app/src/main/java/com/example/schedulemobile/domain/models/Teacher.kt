package com.example.schedulemobile.domain.models

import com.example.schedulemobile.domain.models.group.Group

data class Teacher(
    val id: Int,
    val name: String,
    val surname: String,
    val middleName: String,
    val groups: List<Group>,
    val disciplines: List<Discipline>,
)