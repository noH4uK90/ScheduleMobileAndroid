package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.Discipline
import retrofit2.http.GET
import retrofit2.http.Query

interface DisciplineEndpoints {

    @GET("Discipline")
    suspend fun getDisciplines(@Query("Search") search: String?): List<Discipline>

    @GET("Discipline/Group")
    suspend fun getGroupDisciplines(@Query("GroupId") groupId: Int): List<Discipline>
}