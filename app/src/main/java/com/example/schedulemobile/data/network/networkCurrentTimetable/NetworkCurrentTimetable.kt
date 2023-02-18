package com.example.schedulemobile.data.network.networkCurrentTimetable

import com.example.schedulemobile.data.network.networkDay.NetworkDay
import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.data.network.networkGroupedTimetable.NetworkGroupedTimetable

data class NetworkCurrentTimetable(
    val group: NetworkGroup,
    val days: List<NetworkGroupedTimetable<NetworkDay>>
)