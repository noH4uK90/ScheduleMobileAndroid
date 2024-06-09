package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.CreateHomeworkCommand
import com.example.schedulemobileandroid.models.Homework
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeworkEndpoints {

    @GET("Homework")
    suspend fun getHomeworks(
        @Query("GroupId") groupId: Int,
        @Query("DisciplineId") disciplineId: Int
    ): List<Homework>

    @POST("Homework")
    suspend fun createHomework(@Body command: CreateHomeworkCommand)

    @DELETE("Homework")
    suspend fun deleteHomework(@Query("Id") id: Int)
}