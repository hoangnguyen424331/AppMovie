package com.example.appmovie.ui.search

import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import java.lang.Exception

class SearchMoviePresenter(private val repository: MovieRepository) : SearchMovieContact.Presenter {

    private var view: SearchMovieContact.View? = null

    override fun getDataSearch(page: Int, query: String) {
        repository.getSearchMovie(page,query,object : OnFetchDataJsonListener<MutableList<SearchMovie?>>{
            override fun onSuccess(data: MutableList<SearchMovie?>) {
              view?.onSearchSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {}

    override fun onStop() {
        this.view = null
    }

    override fun onView(view: SearchMovieContact.View?) {
        this.view = view
    }
}
