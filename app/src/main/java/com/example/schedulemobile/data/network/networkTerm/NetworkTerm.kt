package com.example.schedulemobile.data.network.networkTerm

import com.example.schedulemobile.data.network.networkCourse.NetworkCourse

data class NetworkTerm(
    val value: Int,
    val courseTerm: Int,
    val course: NetworkCourse
)
