package com.example.appmovie.data.model

data class HotMovie(
    val id: Int?,
    val title: String?,
    val voteAverage: Double?,
    val posterPath: String?
)

object HotMovieEntry {
    const val MOVIE = "result"
    const val ID = "id"
    const val TITLE = "title"
    const val VOTE = "vote_average"
    const val URL_IMAGE = "poster_path"
}
