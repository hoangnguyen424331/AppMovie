package com.example.appmovie.utils

<<<<<<< HEAD
=======
import com.example.appmovie.BuildConfig

>>>>>>> master
object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    const val BASE_LANGUAGE = "&language=en-US"
    const val BASE_PAGE = "&page="
    const val DEFAULT_PAGE = 1
<<<<<<< HEAD
    private const val API_KEY = "api_key=7fd819c456160075902b7c936b1311aa"
    const val BASE_API_KEY = API_KEY
=======
    private const val API_KEY = "api_key="
    const val BASE_API_KEY = API_KEY + BuildConfig.API_KEY
>>>>>>> master
}
