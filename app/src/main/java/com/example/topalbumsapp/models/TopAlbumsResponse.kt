package com.example.topalbumsapp.models

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(val feed: Feed)

data class Feed(val entry: List<AlbumDto>)

data class AlbumDto(
    @SerializedName("im:name") val name: Label?,
    @SerializedName("im:image") val images: List<Image>?,
    @SerializedName("im:artist") val artist: Label?,
    @SerializedName("im:releaseDate") val releaseDate: ReleaseDate?,
    @SerializedName("im:price") val price: Price?,
    val category: Category?,
    val id: Id?,
    val title: Label?,
    val rights: Label?,
    val link: Link?
)

data class Label(val label: String?)

data class Image(val label: String?)

data class ReleaseDate(
    val label: String?,
    val attributes: Attributes?
)

data class Price(
    val label: String?,
    val attributes: Attributes?
)

data class Category(
    val attributes: Attributes?
)

data class Id(
    val attributes: Attributes?
)

data class Link(
    val attributes: LinkAttributes?
)

data class Attributes(val label: String?)

data class LinkAttributes(val href: String?)
