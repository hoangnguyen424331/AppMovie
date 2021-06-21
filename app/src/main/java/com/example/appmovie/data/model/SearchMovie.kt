package com.example.appmovie.data.model

data class SearchMovie(
    val id: Int?,
    val title: String?,
    val posterPath: String?
)

object SearchMovieEntry {
    const val MOVIE = "results"
    const val ID = "id"
    const val TITLE = "title"
    const val URL_IMAGE = "poster_path"
}
