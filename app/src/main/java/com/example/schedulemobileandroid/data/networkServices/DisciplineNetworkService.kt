package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.DisciplineEndpoints
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.Discipline
import javax.inject.Inject

interface DisciplineApiService {
    suspend fun getDisciplines(search: String?): Resource<List<Discipline>>

    suspend fun getGroupDisciplines(groupId: Int): Resource<List<Discipline>>
}

class DisciplineNetworkService @Inject constructor(
    private val endpoints: DisciplineEndpoints
): DisciplineApiService {
    override suspend fun getDisciplines(search: String?): Resource<List<Discipline>> {
        return try {
            Resource.Success(
                data = endpoints.getDisciplines(search)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getGroupDisciplines(groupId: Int): Resource<List<Discipline>> {
        return try {
            Resource.Success(
                data = endpoints.getGroupDisciplines(groupId)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }

}