package com.example.schedulemobile.data.network.networkClassroom

import com.example.schedulemobile.data.network.networkClassroomType.NetworkClassroomType

data class NetworkClassroom(
    val id: Int,
    val cabinet: String,
    val types: List<NetworkClassroomType>
)