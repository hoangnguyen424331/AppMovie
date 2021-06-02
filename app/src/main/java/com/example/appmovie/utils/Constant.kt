package com.example.appmovie.utils

import com.example.appmovie.BuildConfig

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    const val BASE_LANGUAGE = "&language=en-US"
    const val BASE_PAGE = "&page="
    const val DEFAULT_PAGE = 1
    private const val API_KEY = "api_key="
    const val BASE_API_KEY = API_KEY + BuildConfig.API_KEY
}
