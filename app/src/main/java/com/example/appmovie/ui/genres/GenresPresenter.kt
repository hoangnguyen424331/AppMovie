package com.example.appmovie.ui.genres

import com.example.appmovie.data.model.Genres
import com.example.appmovie.data.model.GenresMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import java.lang.Exception

class GenresPresenter internal constructor(private val repository: MovieRepository?) :
    GenresContact.Presenter {

    private var view: GenresContact.View? = null

    override fun getGenres() {
        repository?.getGenre(object : OnFetchDataJsonListener<MutableList<Genres?>> {
            override fun onSuccess(data: MutableList<Genres?>) {
                view?.onGetGenresSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }

        })
    }

    override fun getGenresMovie(page: Int, query: String) {
        repository?.getGenreMovie(
            page,
            query,
            object : OnFetchDataJsonListener<MutableList<GenresMovie?>> {
                override fun onSuccess(data: MutableList<GenresMovie?>) {
                    view?.onGetGenresMovieSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            })
    }

    override fun onStart() {
        getGenres()
    }

    override fun onStop() {
        this.view = null
    }

    override fun onView(view: GenresContact.View?) {
        this.view = view
    }
}
