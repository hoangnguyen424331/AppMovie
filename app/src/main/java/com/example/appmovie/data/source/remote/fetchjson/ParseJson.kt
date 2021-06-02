package com.example.appmovie.data.source.remote.fetchjson

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.model.HotMovieEntry
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
}
