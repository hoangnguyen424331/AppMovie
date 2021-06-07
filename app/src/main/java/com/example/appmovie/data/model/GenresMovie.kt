package com.example.appmovie.data.model

data class GenresMovie(
    val id: Int?,
    val title: String?,
    val urlImage: String?,
)

object GenresMovieEntry {
    const val ID = "id"
    const val TITLE = "title"
    const val URL_IMAGE = "poster_path"
    const val MOVIE = "results"
}
