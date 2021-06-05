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
<<<<<<< HEAD
        remote.getHotMovies(page, hotMovieType, listener)
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
=======
        remote.getHotMoves(page, hotMovieType, listener)
>>>>>>> master
    }
}
