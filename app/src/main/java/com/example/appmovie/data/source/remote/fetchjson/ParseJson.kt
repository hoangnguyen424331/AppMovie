package com.example.appmovie.data.source.remote.fetchjson

import com.example.appmovie.data.model.*
import com.example.appmovie.utils.KeyEntityType
import org.json.JSONObject

class ParseJson {

    fun movieParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        HotMovie(
            getInt(HotMovieEntry.ID),
            getString(HotMovieEntry.TITLE),
            getDouble(HotMovieEntry.VOTE),
            getString(HotMovieEntry.URL_IMAGE)
        )
    }

    fun genresParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        Genres(
            getInt(GenresEntry.ID),
            getString(GenresEntry.NAME)
        )
    }

    fun genresMovieParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        GenresMovie(
            getInt(GenresMovieEntry.ID),
            getString(GenresMovieEntry.TITLE),
            getString(GenresMovieEntry.URL_IMAGE)
        )
    }

    fun detailMovieParseJson(jsonObject: JSONObject?) = jsonObject?.run {
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

    fun actorParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        Actor(
            id = getInt(ActorEntry.ID),
            name = getString(ActorEntry.NAME),
            imageUrl = getString(ActorEntry.IMAGE_URL)
        )
    }

    fun detailActorParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        DetailActor(
            id = getInt(DetailActorEntry.ID),
            name = getString(DetailActorEntry.NAME),
            imageUrl = getString(DetailActorEntry.IMAGE_URL),
            birthday = getString(DetailActorEntry.BIRTHDAY),
            gender = getInt(DetailActorEntry.GENDER),
            address = getString(DetailActorEntry.ADDRESS)
        )
    }

    fun videoParseJson(jsonObject: JSONObject?) = jsonObject?.run {
        VideoMovie(
            getString(VideoMovieEntry.ID),
            getString(VideoMovieEntry.KEY),
            getString(VideoMovieEntry.TYPE)
        )
    }
}
