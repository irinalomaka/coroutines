package com.nennos.kointestapp.ui.fragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.repositoty.UserLocalRepository
import com.nennos.kointestapp.repositoty.UserNetworkRepository
import com.nennos.kointestapp.ui.base.BaseViewModel
import com.nennos.kointestapp.utils.Mappers
import kotlinx.coroutines.*

class MainFragmentViewModel(
    private val networkRepository: UserNetworkRepository,
    private val localRepository: UserLocalRepository
) : BaseViewModel() {

    val userList = MutableLiveData<List<User>>(emptyList())

    val usersLiveData: LiveData<List<User>>
        get() = localRepository.loadUsers()

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = networkRepository.fetchUsers()
            if (response.isSuccessful) {
                localRepository.saveUsers(
                    Mappers.mapNetworkModelsToEntity(
                        response.body() ?: emptyList()
                    )
                )
            }
        }
    }
}