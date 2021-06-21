package com.example.appmovie.data.source.local.database.dao

import com.example.appmovie.data.model.Favorite

interface FavoriteDao {
    fun insertFavorite(favorite: Favorite): Boolean
    fun deleteFavorite(id: Int): Boolean
    fun checkFavorite(id: Int): Boolean
    fun getListFavorite(): MutableList<Favorite>
}
