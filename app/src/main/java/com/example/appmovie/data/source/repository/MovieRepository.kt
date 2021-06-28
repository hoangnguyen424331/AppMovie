package com.example.appmovie.data.source.repository

import com.example.appmovie.data.model.*
import com.example.appmovie.data.source.MovieDataSource
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.utils.ActorDetailType
import com.example.appmovie.utils.DetailMovieType
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

    fun getGenreMovie(
        page: Int,
        query: String,
        listener: OnFetchDataJsonListener<MutableList<GenresMovie?>>
    ) {
        remote.getGenresMovie(page, query, listener)
    }

    fun getDetailMovie(
        id: Int,
        listener: OnFetchDataJsonListener<DetailMovie>,
    ) {
        remote.getDataDetailMovie(id, DetailMovieType.MOVIE_DETAIL, listener)
    }

    fun getVideo(
        idMovieDetails: Int,
        listener: OnFetchDataJsonListener<List<VideoMovie>>
    ) {
        remote.getDataDetailMovie(idMovieDetails, DetailMovieType.VIDEO, listener)
    }

    fun getRecommend(
        idMovieDetails: Int,
        listener: OnFetchDataJsonListener<List<HotMovie>>
    ) {
        remote.getDataDetailMovie(idMovieDetails, DetailMovieType.RECOMMEND, listener)
    }

    fun getActor(
        idMovieDetails: Int,
        listener: OnFetchDataJsonListener<List<Actor>>
    ) {
        remote.getDataDetailMovie(idMovieDetails, DetailMovieType.ACTOR, listener)
    }

    fun getActorDetail(
        idActor: Int,
        listener: OnFetchDataJsonListener<DetailActor>
    ) {
        remote.getDataInActor(idActor, ActorDetailType.ACTOR, listener)
    }

    fun getExternal(
        idActor: Int,
        listener: OnFetchDataJsonListener<External>
    ) {
        remote.getDataInActor(idActor, ActorDetailType.EXTERNAL, listener)
    }

    fun getSearchMovie(
        page: Int,
        query: String,
        listener: OnFetchDataJsonListener<MutableList<SearchMovie?>>
    ) {
        remote.getDataSearch(page, query, listener)
    }

    fun getMovieByActor(
        idActor: Int,
        listener: OnFetchDataJsonListener<List<HotMovie>>
    ) {
        remote.getMovieByActor(idActor, listener)
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
