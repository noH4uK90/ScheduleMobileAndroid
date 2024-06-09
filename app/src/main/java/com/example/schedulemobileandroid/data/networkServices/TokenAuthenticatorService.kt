package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.AuthorizationResponse
import com.example.schedulemobileandroid.models.RefreshCommand
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TokenAuthenticatorService @Inject constructor(
    private val dataStoreService: DataStoreService
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == null) {
            val auth = runBlocking {
                dataStoreService.getAuthData()
            }

            return response.request.newBuilder()
                .header("Authorization", "Bearer ${auth?.accessToken ?: ""}")
                .build()
        }

        if (response.code == 401) {
            val refreshResult = runBlocking {
                val auth = dataStoreService.getAuthData()
                response.request.newBuilder()

                refreshToken(auth?.refreshToken ?: "", auth?.accessToken ?: "")
            }

            return when (refreshResult) {
                is Resource.Success -> {
                    runBlocking {
                        dataStoreService.saveAuthData(refreshResult.data)
                    }

                    response.request.newBuilder()
                        .header("Authorization", refreshResult.data?.accessToken ?: "")
                        .build()
                }
                else -> null
            }
        }

        return response.request
    }

    private suspend fun refreshToken(accessToken: String?, refreshToken: String?): Resource<AuthorizationResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:2591/Account/refresh")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(AccountNetworkService::class.java)
        val command = RefreshCommand(accessToken ?: "", refreshToken ?: "")
        return service.refresh(command)
    }
}