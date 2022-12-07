package com.example.schedulemobile.data.network

data class NetworkClassroom(
    val id: Int,
    val cabinet: String,
    val types: List<NetworkClassroomType>
)