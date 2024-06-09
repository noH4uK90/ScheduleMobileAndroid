package com.example.schedulemobileandroid.presentation.viewModels

import android.util.Printer
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.AccountApiService
import com.example.schedulemobileandroid.data.services.ISharedPreferencesService
import com.example.schedulemobileandroid.models.Account
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.services.NavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountNetworkService: AccountApiService,
    private val sharedPreferencesService: ISharedPreferencesService,
    private val navigationService: NavigationService
): ViewModel(), DefaultLifecycleObserver {

    private val _account = MutableStateFlow<FullAccount?>(null)
    val account: StateFlow<FullAccount?> get() = _account

    init {
        _account.value = sharedPreferencesService.getAccount()
    }

    fun logOut() {
        viewModelScope.launch {
            sharedPreferencesService.clear()
            navigationService.logOut()
        }
    }
}