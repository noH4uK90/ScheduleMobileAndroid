package com.example.schedulemobile.domain.repository

import com.example.schedulemobile.domain.models.group.GroupList
import com.example.schedulemobile.domain.util.Resource

interface GroupRepository {
    suspend fun getGroups(count: Int = 100): Resource<GroupList>
}