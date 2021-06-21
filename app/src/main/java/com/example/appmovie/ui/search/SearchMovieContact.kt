package com.example.appmovie.ui.search

import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

interface SearchMovieContact {

    interface View {
        fun onSearchSuccess(listSearchMovie: MutableList<SearchMovie?>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getDataSearch(page: Int, query: String)
    }
}
