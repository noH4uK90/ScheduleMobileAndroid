package com.example.schedulemobileandroid.models

data class Student(
    val id: Int,
    val group: GroupModel,
    val login: String,
    val email: String,
    val name: String,
    val surname: String,
    val middleName: String?
)
