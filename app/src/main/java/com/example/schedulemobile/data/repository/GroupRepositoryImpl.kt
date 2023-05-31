package com.example.schedulemobile.data.repository

import com.example.schedulemobile.data.GroupApi
import com.example.schedulemobile.data.mappers.toGroupList
import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.repository.GroupRepository
import com.example.schedulemobile.domain.util.Resource
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val api: GroupApi
): GroupRepository {

    override suspend fun getGroups(pageNumber: Int, pageSize: Int): Resource<GroupList> {
        return try {
            Resource.Success(
                data = api.getGroups(
                    pageNumber = pageNumber,
                    pageSize = pageSize
                ).toGroupList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Неизвестная ошибка.")
        }
    }

    override suspend fun loadMoreGroups(pageNumber: Int, pageSize: Int): Resource<GroupList> {
        return try {
            Resource.Success(
                data = api.getGroups(
                    pageNumber = pageNumber,
                    pageSize = pageSize
                ).toGroupList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Неизвестная ошибка.")
        }
    }
}
