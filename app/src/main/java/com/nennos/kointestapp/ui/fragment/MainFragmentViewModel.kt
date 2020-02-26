package com.nennos.kointestapp.ui.fragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.repositoty.UserLocalRepository
import com.nennos.kointestapp.repositoty.UserNetworkRepository
import com.nennos.kointestapp.ui.base.BaseViewModel
import com.nennos.kointestapp.utils.Mappers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainFragmentViewModel(
    private val networkRepository: UserNetworkRepository,
    private val localRepository: UserLocalRepository
) : BaseViewModel() {

    val usersLiveData = MutableLiveData<List<User>>(emptyList())

    init {
        loadUsers()
        fetchUsers()
    }

    private fun fetchUsers() {
        coroutineScope.async {
            val response = networkRepository.fetchUsers()
            if (response.isSuccessful) {
                localRepository.saveUsers(
                    Mappers.mapNetworkModelsToEntity(response.body() ?: emptyList())
                )
            } else {
                handleError(response.errorBody())
            }
        }
    }

    private fun loadUsers() {
        viewModelScope.async {
            var users: List<User>? = null
            withContext(Dispatchers.IO) {
                users = localRepository.loadUsers()
            }

            usersLiveData.value = users
        }
    }
}