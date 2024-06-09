package com.example.schedulemobileandroid.models

data class Account(
    val id: Int,
    val name: Name,
    val surname: Surname,
    val middleName: MiddleName?,
    val email: String,
    val role: Role
)

data class FullAccount(
    val id: Int,
    val login: String,
    val passwordHash: String,
    val name: Name,
    val surname: Surname,
    val middleName: MiddleName?,
    val email: String,
    val role: Role,
    val isDeleted: Boolean
)

data class LoginCommand(
    val login: String,
    val password: String
)

data class LogoutCommand(
    val accessToken: String,
    val refreshToken: String
)

data class RefreshCommand(
    val refreshToken: String,
    val accessToke: String
)

data class ChangePasswordCommand(
    val id: Int,
    val password: String,
    val newPassword: String
)

data class RestorePasswordCommand(
    val email: String
)