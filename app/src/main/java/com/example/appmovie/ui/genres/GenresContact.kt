package com.example.appmovie.ui.genres

import com.example.appmovie.data.model.Genres
import com.example.appmovie.data.model.GenresMovie
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

class GenresContact {

    interface View {
        fun onGetGenresSuccess(listGenres: MutableList<Genres?>)
        fun onGetGenresMovieSuccess(listGenresMovie: MutableList<GenresMovie?>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getGenres()
        fun getGenresMovie(page: Int, query: String)
    }
}
