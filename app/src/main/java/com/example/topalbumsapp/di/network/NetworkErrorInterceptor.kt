package com.example.topalbumsapp.di.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            val code = response.code
            val errorBody = response.peekBody(Long.MAX_VALUE).string()

            val message = when (code) {
                400 -> "Bad Request"
                401 -> "Unauthorized"
                403 -> "Forbidden"
                404 -> "Not Found"
                500 -> "Server error"
                else -> "HTTP error $code"
            }

            throw ApiException(message, code, errorBody)
        }

        return response
    }
}
