package com.example.schedulemobileandroid.models

data class AuthorizationResponse(
    val accessToken: String,
    val refreshToken: String,
    val account: FullAccount
)
