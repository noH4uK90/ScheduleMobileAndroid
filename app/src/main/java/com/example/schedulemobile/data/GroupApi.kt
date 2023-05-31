package com.example.schedulemobile.data

import com.example.schedulemobile.data.network.networkGroup.NetworkGroupList
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupApi {

    @GET("Group")
    suspend fun getGroups(
        @Query("PageNumber") pageNumber: Int,
        @Query("PageSize") pageSize: Int = 100
    ): NetworkGroupList
}