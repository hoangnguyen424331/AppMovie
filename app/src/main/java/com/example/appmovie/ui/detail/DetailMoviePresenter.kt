package com.example.appmovie.ui.detail

import com.example.appmovie.data.model.Actor
import com.example.appmovie.data.model.DetailMovie
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.model.VideoMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import java.lang.Exception

class DetailMoviePresenter(
    private val repository: MovieRepository
) : DetailMovieContact.Presenter {

    private var view: DetailMovieContact.View? = null

    override fun getMovieDetail(id: Int) {
        repository.getDetailMovie(id, object : OnFetchDataJsonListener<DetailMovie> {
            override fun onSuccess(data: DetailMovie) {
                view?.loadContentMovieOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getVideoMovie(idMovieDetail: Int) {
        repository.getVideo(idMovieDetail, object : OnFetchDataJsonListener<List<VideoMovie>> {
            override fun onSuccess(data: List<VideoMovie>) {
                view?.loadVideoOnSuccess(data.firstOrNull())
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getListRecommend(idMovieDetail: Int) {
        repository.getRecommend(idMovieDetail, object : OnFetchDataJsonListener<List<HotMovie>> {
            override fun onSuccess(data: List<HotMovie>) {
                view?.loadRecommendOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getActor(idMovieDetail: Int) {
        repository.getActor(idMovieDetail, object : OnFetchDataJsonListener<List<Actor>> {
            override fun onSuccess(data: List<Actor>) {
                view?.loadListActorOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {}

    override fun onStop() {
        view = null
    }

    override fun onView(view: DetailMovieContact.View?) {
        this.view = view
    }
}
