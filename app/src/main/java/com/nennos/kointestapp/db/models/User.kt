package com.nennos.kointestapp.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "repos_url") var reposUrl: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "type") var type: String
)