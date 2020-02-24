package com.nennos.kointestapp.repositoty

import androidx.lifecycle.LiveData
import com.nennos.kointestapp.db.models.User

interface UserLocalRepository {

    fun loadUsers() : LiveData<List<User>>

    suspend fun saveUsers(users: List<User>)
}