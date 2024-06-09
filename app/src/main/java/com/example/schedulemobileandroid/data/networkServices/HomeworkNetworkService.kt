package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.HomeworkEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.CreateHomeworkCommand
import com.example.schedulemobileandroid.models.Homework
import javax.inject.Inject

interface HomeworkApiService {
    suspend fun getHomeworks(groupId: Int, disciplineId: Int): Resource<List<Homework>>

    suspend fun createHomework(command: CreateHomeworkCommand): Resource<Unit>

    suspend fun deleteHomework(id: Int): Resource<Unit>
}

class HomeworkNetworkService @Inject constructor(
    private val endpoints: HomeworkEndpoints
): HomeworkApiService {
    override suspend fun getHomeworks(groupId: Int, disciplineId: Int): Resource<List<Homework>> {
        return try {
            Resource.Success(
                data = endpoints.getHomeworks(groupId, disciplineId)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun createHomework(command: CreateHomeworkCommand): Resource<Unit> {
        return try {
            Resource.Success(
                data = endpoints.createHomework(command)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun deleteHomework(id: Int): Resource<Unit> {
        return try {
            Resource.Success(
                data = endpoints.deleteHomework(id)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

}