package com.example.schedulemobileandroid.di

import com.example.schedulemobileandroid.data.networkServices.AccountNetworkService
import com.example.schedulemobileandroid.data.networkServices.TimetableNetworkService
import com.example.schedulemobileandroid.data.networkServices.GroupNetworkService
import com.example.schedulemobileandroid.data.networkServices.AccountApiService
import com.example.schedulemobileandroid.data.networkServices.DataStoreService
import com.example.schedulemobileandroid.data.networkServices.DisciplineApiService
import com.example.schedulemobileandroid.data.networkServices.DisciplineNetworkService
import com.example.schedulemobileandroid.data.networkServices.GroupApiService
import com.example.schedulemobileandroid.data.networkServices.HomeworkApiService
import com.example.schedulemobileandroid.data.networkServices.HomeworkNetworkService
import com.example.schedulemobileandroid.data.networkServices.IDataStoreService
import com.example.schedulemobileandroid.data.networkServices.StudentApiService
import com.example.schedulemobileandroid.data.networkServices.StudentNetworkService
import com.example.schedulemobileandroid.data.networkServices.TeacherApiService
import com.example.schedulemobileandroid.data.networkServices.TeacherNetworkService
import com.example.schedulemobileandroid.data.networkServices.TimetableApiService
import com.example.schedulemobileandroid.data.networkServices.TokenAuthenticatorService
import com.example.schedulemobileandroid.data.services.ISharedPreferencesService
import com.example.schedulemobileandroid.data.services.SharedPreferencesService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Authenticator
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticatorService(
        authenticatorService: TokenAuthenticatorService
    ): Authenticator

    @Binds
    @Singleton
    abstract fun bindDataStoreService(
        dataStoreService: DataStoreService
    ): IDataStoreService

    @Binds
    @Singleton
    abstract fun bindAccountNetworkService(
        accountNetworkService: AccountNetworkService
    ): AccountApiService

    @Binds
    @Singleton
    abstract fun bindDisciplineNetworkService(
        disciplineNetworkService: DisciplineNetworkService
    ): DisciplineApiService

    @Binds
    @Singleton
    abstract fun bindGroupNetworkService(
        groupNetworkService: GroupNetworkService
    ): GroupApiService

    @Binds
    @Singleton
    abstract fun bindHomeworkNetworkService(
        homeworkNetworkService: HomeworkNetworkService
    ): HomeworkApiService

    @Binds
    @Singleton
    abstract fun bindStudentNetworkService(
        studentNetworkService: StudentNetworkService
    ): StudentApiService

    @Binds
    @Singleton
    abstract fun bindTeacherNetworkService(
        teacherNetworkService: TeacherNetworkService
    ): TeacherApiService

    @Binds
    @Singleton
    abstract fun bindTimetableNetworkService(
        timetableNetworkService: TimetableNetworkService
    ): TimetableApiService

    @Binds
    @Singleton
    abstract fun bindSharedPreferencesService(
        sharedPreferencesService: SharedPreferencesService
    ): ISharedPreferencesService
}