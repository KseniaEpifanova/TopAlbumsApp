package com.example.topalbumsapp.repositories

import com.example.topalbumsapp.models.Album

interface AlbumsRepository {
    suspend fun getAlbums(): Result<List<Album>>
}