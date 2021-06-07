package com.example.appmovie.data.model

data class Genres(
    val id: Int?,
    val name: String?,
    val selected: Boolean = false,
    val positionSelected: Int? = null
)

object GenresEntry {
    const val ID = "id"
    const val NAME = "name"
    const val LIST_GENRES = "genres"
}
