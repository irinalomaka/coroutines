package com.nennos.kointestapp.repositoty

import androidx.lifecycle.LiveData
import com.nennos.kointestapp.db.models.User

interface UserLocalRepository {

    fun loadUsersLiveData(): LiveData<List<User>>

    fun loadUsers(): List<User>

    fun loadUser(userId: Long): User

    suspend fun saveUsers(users: List<User>)
}