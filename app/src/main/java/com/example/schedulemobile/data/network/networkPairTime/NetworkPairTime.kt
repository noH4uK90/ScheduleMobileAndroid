package com.example.schedulemobile.data.network.networkPairTime

import com.example.schedulemobile.data.network.networkPairTimeType.NetworkPairTimeType

data class NetworkPairTime(
    val id: Int,
    val start: String,
    val end: String,
    val pairNumber: Int,
    val type: NetworkPairTimeType?
)