package com.example.schedulemobile.data.network.networkTimetable

import com.example.schedulemobile.data.network.networkDay.NetworkDay
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.data.network.networkPair.NetworkPair
import com.example.schedulemobile.data.network.networkTimetableType.NetworkTimetableType
import com.example.schedulemobile.data.network.networkWeekType.NetworkWeekType

data class NetworkTimetable(
    val day: NetworkDay,
    val group: NetworkGroup,
    val id: Int,
    val type: NetworkTimetableType,
    val pairs: List<NetworkPair>,
    val weekType: NetworkWeekType
)