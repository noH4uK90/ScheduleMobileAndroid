package com.example.schedulemobile.data.network.networkTimetable

import com.example.schedulemobile.data.network.*
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup

data class NetworkTimetable(
    val day: NetworkDay,
    val group: NetworkGroup,
    val id: Int,
    val type: NetworkTimetableType,
    val pairs: List<NetworkPair>,
    val weekType: NetworkWeekType
)