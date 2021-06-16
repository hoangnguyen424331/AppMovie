package com.example.appmovie.data.model

data class VideoMovie(
    val id: String?,
    val key: String?,
    val type: String?
)

object VideoMovieEntry {
    const val ID = "id"
    const val KEY = "key"
    const val TYPE = "type"
    const val VIDEOS = "results"
}
