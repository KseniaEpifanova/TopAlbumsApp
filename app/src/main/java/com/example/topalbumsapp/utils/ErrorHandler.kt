package com.example.topalbumsapp.utils

import com.example.topalbumsapp.di.network.ApiException
import java.io.IOException

object ErrorHandler {

    fun getUserFriendlyMessage(throwable: Throwable): String {
        return when (throwable) {
            is ApiException -> "Error ${throwable.code}: ${throwable.message}"
            is IOException -> "Network issue. Please check your internet connection."
            else -> "Something went wrong. Please try again."
        }
    }
}