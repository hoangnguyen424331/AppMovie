package com.example.appmovie.ui.detail.movie

import com.example.appmovie.data.model.Actor
import com.example.appmovie.data.model.DetailMovie
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.model.VideoMovie
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

class DetailMovieContact {

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
    }
}
