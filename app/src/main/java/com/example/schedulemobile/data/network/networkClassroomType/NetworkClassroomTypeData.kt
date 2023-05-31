package com.example.schedulemobile.data.network.networkClassroomType

data class NetworkClassroomTypeData(
    val items: List<NetworkClassroomType>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)
