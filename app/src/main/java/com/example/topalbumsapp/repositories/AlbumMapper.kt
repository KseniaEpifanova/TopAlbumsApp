package com.example.topalbumsapp.repositories

import com.example.topalbumsapp.models.Album
import com.example.topalbumsapp.models.AlbumDto
import javax.inject.Inject

class AlbumMapper @Inject constructor() {
    private fun map(dto: AlbumDto): Album{
        return Album(
            id = dto.id?.attributes?.label ?: "",
            name = dto.name?.label ?: "Unknown",
            artist = dto.artist?.label ?: "Unknown",
            imageUrl = dto.images?.lastOrNull()?.label ?: "",
            genre = dto.category?.attributes?.label ?: "Unknown",
            price = dto.price?.label ?: "Unknown",
            releaseDate = dto.releaseDate?.attributes?.label ?: "Unknown"
        )
    }

    fun mapList(dtos: List<AlbumDto>): List<Album> = dtos.map { map(it) }
}
