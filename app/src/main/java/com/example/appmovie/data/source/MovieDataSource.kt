package com.example.appmovie.data.source

import com.example.appmovie.data.model.GenresMovie
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.utils.DetailMovieType
import com.example.appmovie.utils.HotMovieType

interface MovieDataSource {

    interface Remote {
        fun getHotMovies(
            page: Int,
            hotMovieType: HotMovieType,
            listener: OnFetchDataJsonListener<MutableList<HotMovie?>>
        )

        fun <T> getGenres(
            listener: OnFetchDataJsonListener<T>
        )

        fun getGenresMovie(
            page: Int,
            query: String,
            listener: OnFetchDataJsonListener<MutableList<GenresMovie?>>
        )

        fun <T> getDataDetailMovie(
            idMovie: Int,
            detailMovieType: DetailMovieType,
            listener: OnFetchDataJsonListener<T>
        )
    }
}
