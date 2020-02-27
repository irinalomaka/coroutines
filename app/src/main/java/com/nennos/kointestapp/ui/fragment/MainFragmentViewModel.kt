package com.nennos.kointestapp.ui.fragment


import androidx.lifecycle.MutableLiveData
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
                withContext(Dispatchers.IO) {
                    localRepository.saveUsers(
                        Mappers.mapNetworkModelsToEntity(response.body() ?: emptyList())
                    )
                }
            } else {
                handleError(response.errorBody())
            }
        }
    }

    private fun loadUsers() {
        coroutineScope.async {
            var users = localRepository.loadUsers()
            withContext(Dispatchers.Main) {
                usersLiveData.value = users
            }
        }
    }
}