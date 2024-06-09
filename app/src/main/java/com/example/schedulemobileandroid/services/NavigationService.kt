package com.example.schedulemobileandroid.services

import android.util.Log
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedulemobileandroid.data.networkServices.GroupApiService
import com.example.schedulemobileandroid.data.networkServices.GroupNetworkService
import com.example.schedulemobileandroid.data.networkServices.IDataStoreService
import com.example.schedulemobileandroid.data.networkServices.StudentApiService
import com.example.schedulemobileandroid.data.networkServices.StudentNetworkService
import com.example.schedulemobileandroid.data.networkServices.TeacherApiService
import com.example.schedulemobileandroid.data.services.ISharedPreferencesService
import com.example.schedulemobileandroid.models.AuthorizationResponse
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.models.GroupModel
import com.example.schedulemobileandroid.models.TeacherFullName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

val LocalNavigationService = compositionLocalOf<NavigationService> {
    error("No NavigationService provided")
}

@HiltViewModel
class NavigationService @Inject constructor (
    private val sharedPreferences: ISharedPreferencesService,
    private val dataStore: IDataStoreService,
    private val studentNetworkService: StudentApiService,
    private val teacherNetworkService: TeacherApiService
): ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated

    private val _account = MutableStateFlow<FullAccount?>(null)
    val account: StateFlow<FullAccount?> get() = _account

    private val _group = MutableStateFlow<GroupModel?>(null)
    val group: StateFlow<GroupModel?> get() = _group

    private val _teacherFullName  = MutableStateFlow<TeacherFullName?>(null)
    val teacherFullName: StateFlow<TeacherFullName?> get() = _teacherFullName

    init {
        val account = sharedPreferences.getAccount()
        val group = sharedPreferences.getGroup()
        _isAuthenticated.value = account != null
        _account.value = account
        _group.value = group

        if (account?.role?.name == "Teacher") {
            getTeacherFullName(account.id)
        }
    }

    fun logIn(authResponse: AuthorizationResponse) {
        viewModelScope.launch {
            dataStore.saveAuthData(authResponse)
            _isAuthenticated.value = true
            _account.value = authResponse.account
            sharedPreferences.setAccount(authResponse.account)

            when (authResponse.account.role.name) {
                "Teacher" -> getTeacherFullName(authResponse.account.id)
                "Student" -> getStudentGroup(authResponse.account.id)
            }
        }
    }

    fun logOut() {
        sharedPreferences.clear()
        _isAuthenticated.value = false
    }

    private fun getStudentGroup(accountId: Int) {
        viewModelScope.launch {
            _group.value = studentNetworkService.getStudentByAccount(accountId).data?.group
            sharedPreferences.setGroup(_group.value)
        }
    }

    private fun getTeacherFullName(accountId: Int) {
        viewModelScope.launch {
            _teacherFullName.value = teacherNetworkService.getTeacherFullNameByAccount(accountId).data
        }
    }
}