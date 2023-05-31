package com.example.schedulemobile.data

import com.example.schedulemobile.data.network.networkCurrentTimetable.NetworkCurrentTimetableList
import retrofit2.http.GET
import retrofit2.http.Query

interface TimetableApi {

    @GET("Timetable/Current")
    suspend fun getCurrentTimetableList(
        @Query("GroupId") groupId: Int,
        @Query("DateCount") dayCount: Int = 7
    ): NetworkCurrentTimetableList
}