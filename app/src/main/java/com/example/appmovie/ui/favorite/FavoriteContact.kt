package com.example.appmovie.ui.favorite

import com.example.appmovie.data.model.Favorite
import com.example.appmovie.utils.BasePresenter
import java.lang.Exception

interface FavoriteContact {

    interface View {
        fun loadFavoriteOnSuccess(movies: MutableList<Favorite>)
        fun onError(exception: Exception?)
    }

    interface Presenter: BasePresenter<View> {
        fun getFavorite(): MutableList<Favorite>
        fun deleteFavorite(idDetailMovie: Int): Boolean
    }
}
