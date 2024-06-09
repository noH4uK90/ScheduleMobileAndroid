package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.PagedList
import com.example.schedulemobileandroid.models.Teacher
import com.example.schedulemobileandroid.models.TeacherFullName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeacherEndpoints {

    @GET("Teacher")
    suspend fun getTeachers(
        @Query("Search") search: String,
        @Query("Page") page: Int
    ): PagedList<Teacher>

    @GET("Teacher/FullName")
    suspend fun getTeacherFullNames(
        @Query("Search") search: String?
    ): List<TeacherFullName>

    @GET("Teacher/Account/{id}")
    suspend fun getTeacherByAccount(
        @Path("id") id: Int
    ): Teacher

    @GET("Teacher/FullName/Account/{id}")
    suspend fun getTeacherFullNameByAccount(@Path("id") id: Int): TeacherFullName
}