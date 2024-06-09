package com.example.schedulemobileandroid.models

data class Employee(
    val id: Int,
    val login: String,
    val email: String,
    val name: String,
    val surname: String,
    val middleName: String?,
)