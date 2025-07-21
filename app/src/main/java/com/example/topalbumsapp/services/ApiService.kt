package com.example.topalbumsapp.services

import com.example.topalbumsapp.models.TopAlbumsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): TopAlbumsResponse
}