package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.Student
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentEndpoints {

    @GET("Student/Account/{id}")
    suspend fun getStudentByAccount(
        @Path("id") id: Int
    ): Student
}