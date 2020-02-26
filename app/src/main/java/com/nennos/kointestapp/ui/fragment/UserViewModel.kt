package com.nennos.kointestapp.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.repositoty.UserLocalRepository
import com.nennos.kointestapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class UserViewModel(private val localRepository: UserLocalRepository) : BaseViewModel() {

    var nameLiveData = MutableLiveData("")
    var typeLiveData = MutableLiveData("")
    var reposLiveData = MutableLiveData("")
    var urlLiveData = MutableLiveData("")

    fun loadUser(userId: Long) {
        viewModelScope.async {
            var user: User? = null
            withContext(Dispatchers.IO) {
                user = localRepository.loadUser(userId)
            }

            setupData(user)
        }
    }

    private fun setupData(user: User?) {
        user?.let {
            nameLiveData.value = "Name: ${it.login}"
            typeLiveData.value = "Type: ${it.type}"
            reposLiveData.value = "Repository: ${it.htmlUrl}"
            urlLiveData.value =  it.avatarUrl
        }
    }
}