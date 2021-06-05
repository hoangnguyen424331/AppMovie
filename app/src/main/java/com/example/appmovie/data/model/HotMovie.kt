package com.example.appmovie.data.model

data class HotMovie(
    val id: Int?,
    val title: String?,
    val voteAverage: Double?,
    val posterPath: String?
)

object HotMovieEntry {
<<<<<<< HEAD
    const val MOVIE = "results"
=======
    const val MOVIE = "result"
>>>>>>> master
    const val ID = "id"
    const val TITLE = "title"
    const val VOTE = "vote_average"
    const val URL_IMAGE = "poster_path"
}
