package com.example.appmovie.utils

enum class DetailMovieType(val path: String) {
    MOVIE_DETAIL("?"),
    ACTOR("/credits?"),
    VIDEO("/videos?"),
    RECOMMEND("/recommendations?")
}
