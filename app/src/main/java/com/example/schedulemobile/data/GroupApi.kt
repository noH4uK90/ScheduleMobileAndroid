package com.example.schedulemobile.data

import com.example.schedulemobile.data.network.networkGroup.NetworkGroup
import com.example.schedulemobile.data.network.networkGroup.NetworkGroupList
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupApi {

    @GET("Group")
    suspend fun getGroups(
        @Query("count") count: Int = 100
    ): NetworkGroupList
}