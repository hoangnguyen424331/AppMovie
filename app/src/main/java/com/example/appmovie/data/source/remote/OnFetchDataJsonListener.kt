package com.example.appmovie.data.source.remote

import java.lang.Exception

interface OnFetchDataJsonListener<T> {
    fun onSuccess(data: T)
<<<<<<< HEAD
    fun onError(exception: Exception?)
=======
    fun onError(exception: Exception)
>>>>>>> master
}
