package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.AuthorizationResponse
import com.example.schedulemobileandroid.models.ChangePasswordCommand
import com.example.schedulemobileandroid.models.LoginCommand
import com.example.schedulemobileandroid.models.LogoutCommand
import com.example.schedulemobileandroid.models.RefreshCommand
import com.example.schedulemobileandroid.models.RestorePasswordCommand
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountEndpoints {
    @POST("Account/login")
    suspend fun login(@Body command: LoginCommand): AuthorizationResponse

    @POST("Account/logout")
    suspend fun logout(@Body command: LogoutCommand)

    @POST("Account/refresh")
    suspend fun refresh(@Body command: RefreshCommand): AuthorizationResponse

    @POST("Account/change_password")
    suspend fun  changePassword(@Body command: ChangePasswordCommand)

    @POST("Account/restore_password")
    suspend fun restorePassword(@Body command: RestorePasswordCommand)
}