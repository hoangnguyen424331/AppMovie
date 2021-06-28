package com.example.appmovie.utils

import com.example.appmovie.data.model.DetailMovie
import com.example.appmovie.data.model.SearchMovie

object FakeData {
    const val QUERY_SEARCH = "nguyen"
    const val PAGE_SEARCH = 1
    const val ID_MOVIE_DETAIL = 445571

    val SEARCH_MODEL = mutableListOf<SearchMovie?>(
        SearchMovie(
            445571, "Game Night", "path 1"
        ),
        SearchMovie(
            396371, "Molly's Game", "path 2"
        )
    )

    val DETAIL_MOVIE_MODEL = DetailMovie(
        "abc",
        "posterUrl",
        0,
        mutableListOf(),
        "title",
        "overview",
        "tagline",
        "releaseDate",
        3.5
    )
}
