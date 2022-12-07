package com.example.schedulemobile.presentation

import com.example.schedulemobile.domain.models.group.GroupList

data class GroupState(
    val groupList: GroupList? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
