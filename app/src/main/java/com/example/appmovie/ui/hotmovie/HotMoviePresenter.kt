package com.example.appmovie.ui.hotmovie

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.utils.Constant
import com.example.appmovie.utils.HotMovieType
import java.lang.Exception

class HotMoviePresenter internal constructor(private val repository: MovieRepository?) :
    HotMovieContact.Presenter {

    private var view: HotMovieContact.View? = null

    override fun getHotMovies(page: Int, hotMovieType: HotMovieType) {
        repository?.getMovie(
            page,
            hotMovieType,
            object : OnFetchDataJsonListener<MutableList<HotMovie?>> {
                override fun onSuccess(data: MutableList<HotMovie?>) {
                    view?.onGetMoviesSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }

    override fun onStart() {
        getHotMovies(Constant.DEFAULT_PAGE, HotMovieType.POPULAR)
    }

    override fun onStop() {
        this.view = null
    }

    override fun onView(view: HotMovieContact.View?) {
        this.view = view
    }
}
