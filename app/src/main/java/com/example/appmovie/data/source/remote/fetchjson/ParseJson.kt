package com.example.appmovie.data.source.remote.fetchjson

import com.example.appmovie.data.model.*
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
}
