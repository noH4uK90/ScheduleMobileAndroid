package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.Lesson
import com.example.schedulemobileandroid.models.Timetable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date


interface TimetableEndpoints {

    @GET("Timetable/Group")
    suspend fun getGroupTimetable(
        @Query("GroupId") groupId: Int,
        @Query("Date") date: String
    ): List<Timetable>

    @GET("Timetable/Teacher")
    suspend fun getTeacherTimetable(
        @Query("TeacherFullNameId") teacherFullNameId: Int,
        @Query("Date") date: String
    ): List<Lesson>
}
