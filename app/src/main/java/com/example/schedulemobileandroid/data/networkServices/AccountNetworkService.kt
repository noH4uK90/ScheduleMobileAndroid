package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.AccountEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.AuthorizationResponse
import com.example.schedulemobileandroid.models.ChangePasswordCommand
import com.example.schedulemobileandroid.models.LoginCommand
import com.example.schedulemobileandroid.models.LogoutCommand
import com.example.schedulemobileandroid.models.RefreshCommand
import com.example.schedulemobileandroid.models.RestorePasswordCommand
import javax.inject.Inject

interface AccountApiService {
    suspend fun login(login: String, password: String): Resource<AuthorizationResponse>
    suspend fun logout(command: LogoutCommand): Resource<Unit>
    suspend fun refresh(command: RefreshCommand): Resource<AuthorizationResponse>
    suspend fun changePassword(command: ChangePasswordCommand): Resource<Unit>
    suspend fun restorePassword(command: RestorePasswordCommand): Resource<Unit>
}

class AccountNetworkService @Inject constructor(
    private val endpoints: AccountEndpoints
): AccountApiService {
    override suspend fun login(login: String, password: String): Resource<AuthorizationResponse> {
        return try {
            val command = LoginCommand(login, password)
            Resource.Success(
                data = endpoints.login(command = command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun logout(command: LogoutCommand): Resource<Unit> {
        return try {
            Resource.Success(
                data = endpoints.logout(command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun refresh(command: RefreshCommand): Resource<AuthorizationResponse> {
        return try {
            Resource.Success(
                data = endpoints.refresh(command = command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun changePassword(command: ChangePasswordCommand): Resource<Unit> {
        return try {
            Resource.Success(
                data = endpoints.changePassword(command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun restorePassword(command: RestorePasswordCommand): Resource<Unit> {
        return try {
            Resource.Success(
                data = endpoints.restorePassword(command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}