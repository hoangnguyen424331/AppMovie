package com.example.appmovie.data.source.local

import android.content.Context
import com.example.appmovie.data.model.Favorite
import com.example.appmovie.data.source.MovieDataSource
import com.example.appmovie.data.source.local.database.DatabaseHelper
import com.example.appmovie.data.source.local.database.dao.FavoriteDaoImpl

class MovieLocalDataSource private constructor(private val favoriteDaoImpl: FavoriteDaoImpl) :
    MovieDataSource.Local {

    override fun saveMovie(favorite: Favorite) = favoriteDaoImpl.insertFavorite(favorite)

    override fun getListFavorite(): MutableList<Favorite> = favoriteDaoImpl.getListFavorite()

    override fun deleteFavorite(idMovie: Int): Boolean = favoriteDaoImpl.deleteFavorite(idMovie)

    override fun checkFavorite(idMovie: Int): Boolean = favoriteDaoImpl.checkFavorite(idMovie)

    companion object {
        var instance: MovieLocalDataSource? = null

        fun getInstance(context: Context): MovieDataSource.Local =
            instance ?: MovieLocalDataSource(
                FavoriteDaoImpl.getInstance(
                    DatabaseHelper.getDatabaseHelper(context)
                )
            ).also {
                instance = it
            }
    }
}
