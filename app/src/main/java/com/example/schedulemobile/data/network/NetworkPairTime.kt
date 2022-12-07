package com.example.schedulemobile.data.network

data class NetworkPairTime(
    val id: Int,
    val start: String,
    val end: String,
    val pairNumber: Int,
    val type: NetworkPairTimeType
)