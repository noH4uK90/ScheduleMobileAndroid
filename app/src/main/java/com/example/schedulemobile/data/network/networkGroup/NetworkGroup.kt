package com.example.schedulemobile.data.network.networkGroup

import com.example.schedulemobile.data.network.networkDiscipline.NetworkDiscipline

data class NetworkGroup(
    val id: Int,
    val name: String,
    val course: Int,
    val disciplines: List<NetworkDiscipline>
)