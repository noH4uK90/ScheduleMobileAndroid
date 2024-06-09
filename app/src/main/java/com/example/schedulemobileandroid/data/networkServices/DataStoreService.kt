package com.example.schedulemobileandroid.data.networkServices

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.schedulemobileandroid.models.AuthorizationResponse
import com.example.schedulemobileandroid.models.FullAccount
import com.example.schedulemobileandroid.models.GroupModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface IDataStoreService {
    suspend fun saveAuthData(authorizationResponse: AuthorizationResponse?)

    suspend fun getAuthData(): AuthorizationResponse?
}

class DataStoreService(private val context: Context): IDataStoreService {
    private val Context.dataStore by preferencesDataStore(name = "auth_prefs")
    private val dataStore = context.dataStore
    private val gson = Gson()

    companion object {
        val AUTH_KEY = stringPreferencesKey("auth_prefs")
    }

    override suspend fun saveAuthData(authorizationResponse: AuthorizationResponse?) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = gson.toJson(authorizationResponse)
        }
    }

    override suspend fun getAuthData(): AuthorizationResponse? {
        val preferences = dataStore.data.first()
        val authorizationResponse = preferences[AUTH_KEY]
        return authorizationResponse?.let { gson.fromJson(it, AuthorizationResponse::class.java) }
    }
}