package com.example.appmovie.data.source.repository

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.MovieDataSource
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.utils.HotMovieType

class MovieRepository private constructor(
    private val remote: MovieDataSource.Remote
) {

    fun getMovie(
        page: Int,
        hotMovieType: HotMovieType,
        listener: OnFetchDataJsonListener<MutableList<HotMovie?>>
    ) {
        remote.getHotMoves(page, hotMovieType, listener)
    }
}
