package com.example.schedulemobileandroid.data.networkServices

import com.example.schedulemobileandroid.data.endpoints.GroupEndpoints
import com.example.schedulemobileandroid.models.Grouped
import com.example.schedulemobileandroid.domain.util.Resource
import com.example.schedulemobileandroid.models.GroupModel
import javax.inject.Inject

interface GroupApiService {
    suspend fun getGroups(search: String?): Resource<List<GroupModel>>
}

class GroupNetworkService @Inject constructor(
    private val endpoints: GroupEndpoints
): GroupApiService {
    override suspend fun getGroups(search: String?): Resource<List<GroupModel>> {
        return try {
            Resource.Success(
                data = endpoints.getGroups(search = search)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}
