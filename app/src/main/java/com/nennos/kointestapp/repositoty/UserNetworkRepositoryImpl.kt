package com.nennos.kointestapp.repositoty

import com.nennos.kointestapp.network.Api
import com.nennos.kointestapp.network.models.UserNetwork
import retrofit2.Call
import retrofit2.Response

class UserNetworkRepositoryImpl(private val networkApi: Api) : UserNetworkRepository {

    override suspend fun fetchUsers(): Response<List<UserNetwork>> {
        return networkApi.fetchUsers()
    }

}