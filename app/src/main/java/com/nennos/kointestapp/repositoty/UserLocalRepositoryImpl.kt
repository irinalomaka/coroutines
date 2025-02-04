package com.nennos.kointestapp.repositoty

import androidx.lifecycle.LiveData
import com.nennos.kointestapp.db.dao.UserDao
import com.nennos.kointestapp.db.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserLocalRepositoryImpl(private val userDao: UserDao) : UserLocalRepository {

    override fun loadUsersLiveData(): LiveData<List<User>> = userDao.getAll()

    override fun loadUser(userId: Long): User = userDao.getUser(userId)

    override suspend fun saveUsers(users: List<User>) = withContext(Dispatchers.IO) {
        userDao.insertAll(*users.toTypedArray())
    }

    override fun loadUsers(): List<User> = userDao.getUsers()

}