package com.example.appmovie.ui.favorite

import com.example.appmovie.data.source.repository.FavoriteRepository

class FavoritePresenter(private val favoriteRepository: FavoriteRepository) :
    FavoriteContact.Presenter {

    private var view: FavoriteContact.View? = null

    override fun getFavorite() = favoriteRepository.getListFavorite()

    override fun deleteFavorite(idDetailMovie: Int) =
        favoriteRepository.deleteFavorite(idDetailMovie)

    override fun onStart() {
        view?.loadFavoriteOnSuccess(getFavorite())
    }

    override fun onStop() {
        view = null
    }

    override fun onView(view: FavoriteContact.View?) {
        this.view = view
    }
}
