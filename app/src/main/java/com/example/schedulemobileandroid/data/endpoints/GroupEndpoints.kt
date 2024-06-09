package com.example.schedulemobileandroid.data.endpoints

import com.example.schedulemobileandroid.models.GroupModel
import com.example.schedulemobileandroid.models.Grouped
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupEndpoints {

    @GET("Group")
    suspend fun getGroups(@Query("Search") search: String?): List<GroupModel>
}