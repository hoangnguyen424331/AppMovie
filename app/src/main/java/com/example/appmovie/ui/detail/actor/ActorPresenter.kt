package com.example.appmovie.ui.detail.actor

import com.example.appmovie.data.model.DetailActor
import com.example.appmovie.data.model.External
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import java.lang.Exception

class ActorPresenter(private val repository: MovieRepository) : ActorContact.Presenter {

    private var view: ActorContact.View? = null

    override fun getActorDetail(id: Int) {
        repository.getActorDetail(id, object : OnFetchDataJsonListener<DetailActor> {
            override fun onSuccess(data: DetailActor) {
                view?.loadContentActorOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getExternal(id: Int) {
        repository.getExternal(id, object : OnFetchDataJsonListener<External> {
            override fun onSuccess(data: External) {
                view?.loadContentExternalOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getListMovieOfActor(id: Int) {
        repository.getMovieByActor(id, object : OnFetchDataJsonListener<List<HotMovie>> {
            override fun onSuccess(data: List<HotMovie>) {
                view?.loadMoviesOnSuccess(data)
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

    override fun onView(view: ActorContact.View?) {
        this.view = view
    }
}
