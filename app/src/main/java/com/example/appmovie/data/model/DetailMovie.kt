package com.example.appmovie.data.model

data class DetailMovie(
    val backdropUrl: String?,
    val posterUrl: String?,
    val id: Int?,
    val genres: List<Genres>,
    val title: String?,
    val overview: String?,
    val tagline: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val isFavourite: Boolean = false
)

object DetailMovieEntry {
    const val BACKDROP_URL = "backdrop_path"
    const val POSTER_URL = "poster_path"
    const val ID = "id"
    const val TITLE = "title"
    const val TAG_LINE = "tagline"
    const val OVERVIEW = "overview"
    const val RELEASE_DATE = "release_date"
    const val VOTE_AVERAGE = "vote_average"
}
