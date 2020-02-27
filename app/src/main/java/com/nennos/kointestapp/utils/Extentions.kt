package com.nennos.kointestapp.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, error: Throwable) {
            result(Result.Failure(call, error))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            result(Result.Success(call, response))
        }
    })
}

inline fun <reified T> Call<T>.executeForResult(): Result<T> {
    return try {
        val response = execute()
        Result.Success(this, response)
    } catch (e: Exception) {
        Result.Failure(this, e)
    }
}