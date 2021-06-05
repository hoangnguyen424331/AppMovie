package com.example.appmovie.ui.hotmovie

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.utils.BasePresenter
import com.example.appmovie.utils.HotMovieType

interface HotMovieContact {

    interface View {
        fun onGetMoviesSuccess(movies: MutableList<HotMovie?>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getHotMovies(page: Int, hotMovieType: HotMovieType)
    }
}
