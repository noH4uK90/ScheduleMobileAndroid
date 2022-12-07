package com.example.schedulemobile.domain.models

data class PairTime(
    val id: Int,
    val start: String,
    val end: String,
    val pairNumber: Int,
    val type: PairTimeType
)