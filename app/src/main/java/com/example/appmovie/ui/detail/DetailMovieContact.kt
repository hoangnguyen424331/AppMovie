package com.example.appmovie.ui.detail

import com.example.appmovie.data.model.*
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

interface DetailMovieContact {

    interface View {
        fun loadContentMovieOnSuccess(detailMovie: DetailMovie)
        fun loadListActorOnSuccess(actors: List<Actor>)
        fun loadRecommendOnSuccess(movies: List<HotMovie>)
        fun loadVideoOnSuccess(video: VideoMovie?)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieDetail(id: Int)
        fun getVideoMovie(idMovieDetail: Int)
        fun getListRecommend(idMovieDetail: Int)
        fun getActor(idMovieDetail: Int)
        fun deleteFavorite(idMovieDetail: Int): Boolean
        fun insertFavorite(favorite: Favorite): Boolean
        fun checkFavorite(idMovieDetail: Int): Boolean
    }
}
