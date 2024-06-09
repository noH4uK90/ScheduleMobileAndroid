package com.example.schedulemobileandroid.presentation

import com.example.schedulemobileandroid.models.GroupModel
import com.example.schedulemobileandroid.models.PagedList

data class GroupState(
    val groupList: PagedList<GroupModel>? = null,
    val currentGroup: GroupModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
