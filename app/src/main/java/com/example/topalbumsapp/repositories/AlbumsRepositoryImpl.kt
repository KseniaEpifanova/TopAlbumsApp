package com.example.topalbumsapp.repositories

import com.example.topalbumsapp.di.network.ApiException
import com.example.topalbumsapp.models.Album
import com.example.topalbumsapp.services.ApiService
import java.io.IOException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: AlbumMapper
): AlbumsRepository {
    override suspend fun getAlbums(): Result<List<Album>> = try {
        val entries = apiService.getTopAlbums().feed.entry
        val albums = mapper.mapList(entries)
        Result.success(albums)
    } catch (e: ApiException) {
        Result.failure(Exception("API error: ${e.message}"))
    } catch (e: IOException) {
        Result.failure(Exception("Network error: Check your connection."))
    } catch (e: Exception) {
        Result.failure(Exception("Unexpected error: ${e.message}"))
    }
}