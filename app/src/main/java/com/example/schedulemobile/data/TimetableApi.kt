package com.example.schedulemobile.data

import com.example.schedulemobile.data.network.networkCurrentTimetable.NetworkCurrentTimetableList
import retrofit2.http.GET
import retrofit2.http.Query

interface TimetableApi {

    @GET("Timetable/Current")
    suspend fun getCurrentTimetableList(
        @Query("groupId") groupId: Int,
        @Query("dayCount") dayCount: Int = 7
    ): NetworkCurrentTimetableList
}