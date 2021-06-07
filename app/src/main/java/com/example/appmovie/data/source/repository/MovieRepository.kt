package com.example.appmovie.data.source.repository

import com.example.appmovie.data.model.Genres
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
        remote.getHotMovies(page, hotMovieType, listener)
    }

    fun getGenre(
        listener: OnFetchDataJsonListener<MutableList<Genres?>>
    ) {
        remote.getGenres(listener)
    }

    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(
            remote: MovieDataSource.Remote
        ): MovieRepository {
            return instance ?: synchronized(this) {
                instance ?: MovieRepository(remote).also {
                    instance = it
                }
            }
        }
    }
}
