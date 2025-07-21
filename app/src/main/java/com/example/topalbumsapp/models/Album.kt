package com.example.topalbumsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val imageUrl: String,
    val genre: String,
    val price: String,
    val releaseDate: String
) : Parcelable