package com.nennos.kointestapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.kointestapp.db.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUser(userId: Long): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(vararg users: User)
}