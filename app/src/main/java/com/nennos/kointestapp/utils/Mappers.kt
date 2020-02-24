package com.nennos.kointestapp.utils

import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.network.models.UserNetwork

object Mappers {

    fun mapNetworkModelsToEntity(users: List<UserNetwork>): List<User> =
        users.map { mapNetworkModel(it) }

    private fun mapNetworkModel(networkModel: UserNetwork): User = User(
        networkModel.id,
        networkModel.login,
        networkModel.repos_url,
        networkModel.avatar_url,
        networkModel.type,
        networkModel.followers_url,
        networkModel.following_url,
        networkModel.url,
        networkModel.html_url
    )
}