package com.nennos.kointestapp.network.models

data class UserNetwork(
    var id: Long,
    var login: String,
    var repos_url: String,
    val avatar_url: String,
    var type: String
)