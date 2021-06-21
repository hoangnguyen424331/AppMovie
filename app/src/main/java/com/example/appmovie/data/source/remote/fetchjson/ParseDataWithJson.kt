package com.example.appmovie.data.source.remote.fetchjson

import com.example.appmovie.data.model.*
import com.example.appmovie.utils.KeyEntityType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {

    fun getJsonFromUrl(urlString: String?): String? {
        try {
            val url = URL(urlString)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.apply {
                connectTimeout = TIME_OUT
                readTimeout = TIME_OUT
                requestMethod = METHOD_GET
                doOutput = true
                connect()
            }
            val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also {
                    line = it
                } != null) {
                stringBuilder.append(line)
            }
            bufferedReader.close()
            httpURLConnection.disconnect()
            return stringBuilder.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntityType: KeyEntityType): Any? {
        try {
            jsonObject?.let {
                return when (keyEntityType) {
                    KeyEntityType.MOVIE_ITEM -> {
                        ParseJson().movieParseJson(it)
                    }
                    KeyEntityType.GENRES_ITEM -> {
                        ParseJson().genresParseJson(it)
                    }
                    KeyEntityType.GENES_MOVIE_ITEM -> {
                        ParseJson().genresMovieParseJson(it)
                    }
                    KeyEntityType.MOVIE_DETAIL -> {
                        ParseJson().detailMovieParseJson(it)
                    }
                    KeyEntityType.ACTOR -> {
                        ParseJson().actorParseJson(it)
                    }
                    KeyEntityType.ACTOR_DETAIL -> {
                        ParseJson().detailActorParseJson(it)
                    }
                    KeyEntityType.VIDEO -> {
                        ParseJson().videoParseJson(it)
                    }
                    KeyEntityType.EXTERNAL -> {
                        ParseJson().detailExternalParseJson(it)
                    }
                    KeyEntityType.SEARCH_MOVIE_ITEM -> {
                        ParseJson().searchMovieParseJson(it)
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    fun parseJsonToData(jsonObject: JSONObject?, keyEntityType: KeyEntityType): Any? {
        return try {
            when (keyEntityType) {
                KeyEntityType.MOVIE_ITEM -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(HotMovieEntry.MOVIE),
                        keyEntityType
                    )
                }
                KeyEntityType.GENRES_ITEM -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(GenresEntry.LIST_GENRES),
                        keyEntityType
                    )
                }
                KeyEntityType.GENES_MOVIE_ITEM -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(GenresMovieEntry.MOVIE),
                        keyEntityType
                    )
                }
                KeyEntityType.MOVIE_DETAIL -> {
                    parseJsonToObject(
                        jsonObject,
                        keyEntityType
                    )
                }
                KeyEntityType.ACTOR -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(ActorEntry.ACTORS),
                        keyEntityType
                    )
                }
                KeyEntityType.ACTOR_DETAIL -> {
                    parseJsonToObject(
                        jsonObject,
                        keyEntityType
                    )
                }
                KeyEntityType.VIDEO -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(VideoMovieEntry.VIDEOS),
                        keyEntityType
                    )
                }
                KeyEntityType.EXTERNAL -> {
                    parseJsonToObject(
                        jsonObject,
                        keyEntityType
                    )
                }
                KeyEntityType.SEARCH_MOVIE_ITEM -> {
                    parseJsonToArray(
                        jsonObject?.getJSONArray(SearchMovieEntry.MOVIE),
                        keyEntityType
                    )
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    fun parseJsonToArray(jsonArray: JSONArray?, typeModel: KeyEntityType): Any? {
        return try {
            val data = mutableListOf<Any?>()
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val jsonObject = jsonArray?.getJSONObject(i)
                data.add(parseJsonToObject(jsonObject, typeModel))
            }
            data.filterNotNull()
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val TIME_OUT = 15000
        private const val METHOD_GET = "GET"
    }
}
