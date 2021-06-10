package com.example.appmovie.data.source.remote.fetchjson

import com.example.appmovie.data.model.*
import com.example.appmovie.utils.KeyEntityType
import org.json.JSONObject

class ParseJson {

    fun movieParseJson(jsonObject: JSONObject) = jsonObject.run {
        HotMovie(
            getInt(HotMovieEntry.ID),
            getString(HotMovieEntry.TITLE),
            getDouble(HotMovieEntry.VOTE),
            getString(HotMovieEntry.URL_IMAGE)
        )
    }

    fun genresParseJson(jsonObject: JSONObject) = jsonObject.run {
        Genres(
            getInt(GenresEntry.ID),
            getString(GenresEntry.NAME)
        )
    }

    fun genresMovieParseJson(jsonObject: JSONObject) = jsonObject.run {
        GenresMovie(
            getInt(GenresMovieEntry.ID),
            getString(GenresMovieEntry.TITLE),
            getString(GenresMovieEntry.URL_IMAGE)
        )
    }

    fun detailMovieParseJson(jsonObject: JSONObject) = jsonObject.run {
        val listGenres = ParseDataWithJson().parseJsonToArray(
            getJSONArray(GenresEntry.LIST_GENRES),
            KeyEntityType.GENRES_ITEM
        ) as List<Genres>

        DetailMovie(
            id = getInt(DetailMovieEntry.ID),
            title = getString(DetailMovieEntry.TITLE),
            posterUrl = getString(DetailMovieEntry.POSTER_URL),
            backdropUrl = getString(DetailMovieEntry.BACKDROP_URL),
            voteAverage = getDouble(DetailMovieEntry.VOTE_AVERAGE),
            overview = getString(DetailMovieEntry.OVERVIEW),
            releaseDate = getString(DetailMovieEntry.RELEASE_DATE),
            tagline = getString(DetailMovieEntry.TAG_LINE),
            genres = listGenres
        )
    }
}
