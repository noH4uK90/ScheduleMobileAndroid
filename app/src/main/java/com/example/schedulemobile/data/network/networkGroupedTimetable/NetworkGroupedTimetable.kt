package com.example.schedulemobile.data.network.networkGroupedTimetable

import com.example.schedulemobile.data.network.networkTimetable.NetworkTimetable

data class NetworkGroupedTimetable<T>(
    val key: T,
    val items: List<NetworkTimetable>
)
