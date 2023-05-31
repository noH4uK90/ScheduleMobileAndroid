package com.example.schedulemobile.domain.repository

import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.util.Resource

interface GroupRepository {
    suspend fun getGroups(pageNumber: Int = 1, pageSize: Int = 100): Resource<GroupList>

    suspend fun loadMoreGroups(pageNumber: Int, pageSize: Int = 100): Resource<GroupList>
}