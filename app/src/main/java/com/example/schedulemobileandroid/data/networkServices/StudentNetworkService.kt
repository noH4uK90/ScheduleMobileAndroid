package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.StudentEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.Student
import javax.inject.Inject

interface StudentApiService {
    suspend fun getStudentByAccount(id: Int): Resource<Student>
}

class StudentNetworkService @Inject constructor(
    private val endpoints: StudentEndpoints
): StudentApiService {
    override suspend fun getStudentByAccount(id: Int): Resource<Student> {
        return try {
            Resource.Success(
                data = endpoints.getStudentByAccount(id)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}