package com.example.schedulemobile.data.network

import com.example.schedulemobile.data.network.networkDiscipline.NetworkDiscipline
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup

data class NetworkTeacher(
    val id: Int,
    val name: String,
    val surname: String,
    val middleName: String,
    val groups: List<NetworkGroup>,
    val disciplines: List<NetworkDiscipline>,
)