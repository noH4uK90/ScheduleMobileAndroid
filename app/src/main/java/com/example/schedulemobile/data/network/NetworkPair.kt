package com.example.schedulemobile.data.network

import com.example.schedulemobile.data.network.networkDiscipline.NetworkDiscipline

data class NetworkPair(
    val id: Int,
    val number: Int,
    val changed: Boolean,
    val timetableId: Int,
    val time: NetworkPairTime?,
    val discipline: NetworkDiscipline?,
    val teacherClassrooms: List<NetworkTeacherClassroom>,
)