package com.nennos.kointestapp.ui.fragment

import androidx.lifecycle.MutableLiveData
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
        coroutineScope.async {
            var user = localRepository.loadUser(userId)
            withContext(Dispatchers.Main) {
                setupData(user)
            }
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