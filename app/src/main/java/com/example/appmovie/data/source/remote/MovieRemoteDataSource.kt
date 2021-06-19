package com.example.appmovie.data.source.remote

import com.example.appmovie.data.model.GenresMovie
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.data.source.MovieDataSource
import com.example.appmovie.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.appmovie.utils.*

@Suppress("DEPRECATION")
class MovieRemoteDataSource : MovieDataSource.Remote {

    private val endParams = Constant.BASE_API_KEY + Constant.BASE_LANGUAGE

    override fun getHotMovies(
        page: Int,
        hotMovieType: HotMovieType,
        listener: OnFetchDataJsonListener<MutableList<HotMovie?>>
    ) {
        val baseUrl = Constant.BASE_URL + MOVIE_TYPE + hotMovieType.path + endParams +
                Constant.BASE_PAGE + page
        GetJsonFromUrl(listener, KeyEntityType.MOVIE_ITEM).execute(baseUrl)
    }

    override fun <T> getGenres(
        listener: OnFetchDataJsonListener<T>,
    ) {
        val baseUrl = Constant.BASE_URL + GENRES_TYPE + endParams
        GetJsonFromUrl(listener, KeyEntityType.GENRES_ITEM).execute(baseUrl)
    }

    override fun getGenresMovie(
        page: Int,
        query: String,
        listener: OnFetchDataJsonListener<MutableList<GenresMovie?>>
    ) {
        val baseUrl = Constant.BASE_URL + DISCOVER_MOVIE + endParams +
                Constant.BASE_SORT_POPULAR +
                Constant.BASE_PAGE + page + Constant.BASE_GENRES + query
        GetJsonFromUrl(listener, KeyEntityType.GENES_MOVIE_ITEM).execute(baseUrl)
    }

    override fun <T> getDataDetailMovie(
        idMovie: Int,
        detailMovieType: DetailMovieType,
        listener: OnFetchDataJsonListener<T>
    ) {
        val baseUrl = Constant.BASE_URL + MOVIE_TYPE + idMovie + detailMovieType.path + endParams

        when (detailMovieType) {
            DetailMovieType.MOVIE_DETAIL -> GetJsonFromUrl(
                listener, KeyEntityType.MOVIE_DETAIL
            ).execute(baseUrl)
            DetailMovieType.VIDEO -> GetJsonFromUrl(
                listener,
                KeyEntityType.VIDEO
            ).execute(baseUrl)
            DetailMovieType.ACTOR -> GetJsonFromUrl(
                listener,
                KeyEntityType.ACTOR
            ).execute(baseUrl)
            DetailMovieType.RECOMMEND -> GetJsonFromUrl(
                listener,
                KeyEntityType.MOVIE_ITEM
            ).execute(baseUrl)
        }
    }

    override fun <T> getDataInActor(
        idActor: Int,
        actorDetailType: ActorDetailType,
        listener: OnFetchDataJsonListener<T>
    ) {
        val baseUrl = Constant.BASE_URL + ACTOR_TYPE + idActor + actorDetailType.path + endParams

        when (actorDetailType) {
            ActorDetailType.ACTOR -> GetJsonFromUrl(
                listener,
                KeyEntityType.ACTOR_DETAIL
            ).execute(baseUrl)
            ActorDetailType.EXTERNAL -> GetJsonFromUrl(
                listener,
                KeyEntityType.EXTERNAL
            ).execute(baseUrl)
        }
    }

    override fun getSearchMovie(
        page: Int,
        query: String,
        listener: OnFetchDataJsonListener<MutableList<SearchMovie?>>
    ) {
        val baseUrl = Constant.BASE_URL + SEARCH_TYPE + endParams +
                Constant.BASE_PAGE + page + Constant.BASE_QUERY + query
        GetJsonFromUrl(listener, KeyEntityType.SEARCH_MOVIE_ITEM).execute(baseUrl)
    }

    companion object {
        private const val MOVIE_TYPE = "movie/"
        private const val GENRES_TYPE = "genre/movie/list?"
        private const val DISCOVER_MOVIE = "discover/movie?"
        private const val ACTOR_TYPE = "person/"
        private const val SEARCH_TYPE = "search/movie?"
        private var instance: MovieRemoteDataSource? = null

        fun getInstance() = instance ?: MovieRemoteDataSource().also {
            instance = it
        }
    }
}
