package com.nennos.kointestapp.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.repositoty.UserLocalRepository
import com.nennos.kointestapp.ui.base.BaseViewModel

class UserViewModel(private val localRepository: UserLocalRepository) : BaseViewModel() {

    var nameLiveData = MutableLiveData("")
    var typeLiveData = MutableLiveData("")
    var reposLiveData = MutableLiveData("")

    fun loadUser(userId: Long) = localRepository.loadUser(userId)

    fun setupData(user: User) {
        nameLiveData.value = "Name: ${user.login}"
        typeLiveData.value = "Type: ${user.type}"
        reposLiveData.value = "Repository: ${user.htmlUrl}"
    }
}