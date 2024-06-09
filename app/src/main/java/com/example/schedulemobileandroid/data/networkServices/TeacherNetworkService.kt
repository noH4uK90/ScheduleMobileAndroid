package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.TeacherEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.Lesson
import com.example.schedulemobileandroid.models.PagedList
import com.example.schedulemobileandroid.models.Teacher
import com.example.schedulemobileandroid.models.TeacherFullName
import javax.inject.Inject

interface TeacherApiService {
    suspend fun getTeachers(search: String, page: Int): Resource<PagedList<Teacher>>
    suspend fun getTeacherFullNames(search: String?): Resource<List<TeacherFullName>>
    suspend fun getTeacherByAccount(id: Int): Resource<Teacher>
    suspend fun getTeacherFullNameByAccount(id: Int): Resource<TeacherFullName>
}

class TeacherNetworkService @Inject constructor(
    private val endpoints: TeacherEndpoints
): TeacherApiService {
    override suspend fun getTeachers(search: String, page: Int): Resource<PagedList<Teacher>> {
        return try {
            Resource.Success(
                data = endpoints.getTeachers(search, page)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getTeacherFullNames(search: String?): Resource<List<TeacherFullName>> {
        return try {
            Resource.Success(
                data = endpoints.getTeacherFullNames(search)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getTeacherByAccount(id: Int): Resource<Teacher> {
        return try {
            Resource.Success(
                data = endpoints.getTeacherByAccount(id)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getTeacherFullNameByAccount(id: Int): Resource<TeacherFullName> {
        return try {
            Resource.Success(
                data = endpoints.getTeacherFullNameByAccount(id)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}