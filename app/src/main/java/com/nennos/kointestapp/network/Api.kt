package com.nennos.kointestapp.network

import com.nennos.kointestapp.network.models.UserNetwork
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("users")
    suspend fun fetchUsers(): Response<List<UserNetwork>>
}