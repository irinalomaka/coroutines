package com.nennos.kointestapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.ResponseBody
import org.json.JSONObject

abstract class BaseViewModel : ViewModel() {

    val errorLiveDatata = MutableLiveData<String>()

    private val completableJob = Job()
    protected val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    protected fun getErrorMessage(responseBody: ResponseBody?): String? {
        return responseBody?.let {
            try {
                val jsonObject = JSONObject(responseBody.string())
                jsonObject.getString("message")
            } catch (e: Exception) {
                e.message
            }
        }
    }

    protected fun handleError(error: ResponseBody?) {
        errorLiveDatata.postValue(getErrorMessage(error))
    }

    override fun onCleared() {
        super.onCleared()
        completableJob.cancel()
    }
}