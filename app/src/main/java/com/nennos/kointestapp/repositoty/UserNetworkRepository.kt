package com.nennos.kointestapp.repositoty

import com.nennos.kointestapp.network.models.UserNetwork
import retrofit2.Response

interface UserNetworkRepository {

     suspend fun fetchUsers(): Response<List<UserNetwork>>
}