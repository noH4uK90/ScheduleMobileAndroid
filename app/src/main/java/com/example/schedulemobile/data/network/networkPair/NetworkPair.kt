package com.example.schedulemobile.data.network.networkPair

import com.example.schedulemobile.data.network.networkPairTime.NetworkPairTime
import com.example.schedulemobile.data.network.networkTeacherClassroom.NetworkTeacherClassroom
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