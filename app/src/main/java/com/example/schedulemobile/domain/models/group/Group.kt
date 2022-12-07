package com.example.schedulemobile.domain.models.group

import com.example.schedulemobile.domain.models.Discipline

data class Group(
    val id: Int,
    val name: String,
    val course: Int,
    val disciplines: List<Discipline>
)