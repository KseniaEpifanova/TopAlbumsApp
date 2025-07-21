package com.example.topalbumsapp.di.network

import java.io.IOException

class ApiException(
    override val message: String,
    val code: Int,
    val body: String?
) : IOException(message) {
    override fun toString(): String {
        return "ApiException(code=$code, message=$message, body=$body)"
    }
}