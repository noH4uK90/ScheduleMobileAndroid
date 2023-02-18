package com.example.schedulemobile.data

import com.example.schedulemobile.data.network.networkGroup.NetworkGroupList
import retrofit2.http.GET

interface GroupApi {

    @GET("Group")
    suspend fun getGroups(): NetworkGroupList
}