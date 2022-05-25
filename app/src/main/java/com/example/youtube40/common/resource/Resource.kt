package com.example.youtube40.common.resource

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(data: T? = null, message: String) :
        Resource<T>(data = data, message = message)
}


