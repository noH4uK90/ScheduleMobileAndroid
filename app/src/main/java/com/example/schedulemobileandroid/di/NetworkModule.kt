package com.example.schedulemobileandroid.di

import android.content.Context
import com.example.schedulemobileandroid.data.endpoints.AccountEndpoints
import com.example.schedulemobileandroid.data.endpoints.DisciplineEndpoints
import com.example.schedulemobileandroid.data.endpoints.GroupEndpoints
import com.example.schedulemobileandroid.data.endpoints.HomeworkEndpoints
import com.example.schedulemobileandroid.data.endpoints.StudentEndpoints
import com.example.schedulemobileandroid.data.endpoints.TeacherEndpoints
import com.example.schedulemobileandroid.data.endpoints.TimetableEndpoints
import com.example.schedulemobileandroid.data.networkServices.DataStoreService
import com.example.schedulemobileandroid.data.networkServices.StudentNetworkService
import com.example.schedulemobileandroid.data.networkServices.TeacherNetworkService
import com.example.schedulemobileandroid.data.networkServices.TokenAuthenticatorService
import com.example.schedulemobileandroid.data.services.SharedPreferencesService
import com.example.schedulemobileandroid.services.NavigationService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.internal.Provider
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideDataStoreService(@ApplicationContext context: Context): DataStoreService = DataStoreService(context)

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://37.18.102.140:5050/api/")
            //.baseUrl("https://gewofo6202.bsite.net/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(tokenAuthenticatorService: TokenAuthenticatorService): OkHttpClient {
        val duration = Duration.ofSeconds(30)
        return OkHttpClient.Builder()
            .authenticator(tokenAuthenticatorService)
            .connectTimeout(duration)
            .readTimeout(duration)
            .writeTimeout(duration)
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenAuthenticatorService(
        dataStoreService: DataStoreService
    ): TokenAuthenticatorService = TokenAuthenticatorService(dataStoreService)

    @Provides
    @Singleton
    fun providesAccountEndpoints(retrofit: Retrofit): AccountEndpoints {
        return retrofit.create(AccountEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun providesDisciplineEndpoints(retrofit: Retrofit): DisciplineEndpoints {
        return retrofit.create(DisciplineEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun providesGroupEndpoints(retrofit: Retrofit): GroupEndpoints {
        return retrofit.create(GroupEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun providesHomeworkEndpoints(retrofit: Retrofit): HomeworkEndpoints {
        return retrofit.create(HomeworkEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun provideStudentEndpoints(retrofit: Retrofit): StudentEndpoints {
        return retrofit.create(StudentEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun providesTeacherEndpoints(retrofit: Retrofit): TeacherEndpoints {
        return retrofit.create(TeacherEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun providesTimetableEndpoints(retrofit: Retrofit): TimetableEndpoints {
        return retrofit.create(TimetableEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun provideNavigationService(
        dataStoreService: DataStoreService,
        sharedPreferencesService: SharedPreferencesService,
        studentNetworkService: StudentNetworkService,
        teacherNetworkService: TeacherNetworkService
    ): NavigationService {
        return NavigationService(
            sharedPreferencesService,
            dataStoreService,
            studentNetworkService,
            teacherNetworkService
        )
    }
}