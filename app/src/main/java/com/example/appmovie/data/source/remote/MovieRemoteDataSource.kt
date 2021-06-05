package com.example.appmovie.data.source.remote

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.MovieDataSource
import com.example.appmovie.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.appmovie.utils.Constant
import com.example.appmovie.utils.HotMovieType
import com.example.appmovie.utils.KeyEntityType

@Suppress("DEPRECATION")
class MovieRemoteDataSource : MovieDataSource.Remote {

    private val endParams = Constant.BASE_API_KEY + Constant.BASE_LANGUAGE

<<<<<<< HEAD
    override fun getHotMovies(
=======
    override fun getHotMoves(
>>>>>>> master
        page: Int,
        hotMovieType: HotMovieType,
        listener: OnFetchDataJsonListener<MutableList<HotMovie?>>
    ) {
        val baseUrl = Constant.BASE_URL + MOVIE_TYPE + hotMovieType.path + endParams +
                Constant.BASE_PAGE + page
        GetJsonFromUrl(listener, KeyEntityType.MOVIE_ITEM).execute(baseUrl)
    }

    companion object {
        private const val MOVIE_TYPE = "movie/"
<<<<<<< HEAD

        private var instance: MovieRemoteDataSource? = null

        fun getInstance() = instance ?: MovieRemoteDataSource().also {
            instance = it
        }
=======
>>>>>>> master
    }
}
