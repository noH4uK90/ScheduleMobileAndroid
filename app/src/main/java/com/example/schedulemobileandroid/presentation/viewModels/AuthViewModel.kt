package com.example.schedulemobileandroid.presentation.viewModels

import android.accounts.Account
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.AccountApiService
import com.example.schedulemobileandroid.data.services.ISharedPreferencesService
import com.example.schedulemobileandroid.data.services.SharedPreferencesService
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.services.NavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountNetworkService: AccountApiService,
    private val sharedPreferencesService: ISharedPreferencesService,
    private val navigationService: NavigationService
): ViewModel(), DefaultLifecycleObserver {

    var authState by mutableStateOf<AuthState>(AuthState.Idle)

    fun logIn(login: String, password: String) {
        viewModelScope.launch {
            authState = AuthState.Loading
            try {
                val account = accountNetworkService.login(login, password)
                if (account.data != null) {
                    authState = AuthState.Success(account.data.account)
                    navigationService.logIn(account.data)
                } else {
                    authState = AuthState.Error(account.message ?: "Неизвестная ошибка")
                }
            } catch (e: Exception) {
                authState = AuthState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}

sealed class AuthState {
    data object Idle: AuthState()
    data object Loading : AuthState()
    data class Success(val account: FullAccount) : AuthState()
    data class Error(val message: String) : AuthState()
}