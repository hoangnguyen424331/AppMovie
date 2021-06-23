package com.example.appmovie.ui.detail.actor

import com.example.appmovie.data.model.DetailActor
import com.example.appmovie.data.model.External
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

interface ActorContact {

    interface View {
        fun loadContentActorOnSuccess(detailActor: DetailActor)
        fun loadContentExternalOnSuccess(external: External)
        fun loadMoviesOnSuccess(movies: List<HotMovie>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getActorDetail(id: Int)
        fun getExternal(id: Int)
        fun getListMovieOfActor(id: Int)
    }
}
