package com.example.appmovie.utils

interface BasePresenter<T> {
    fun onStart()
    fun onStop()
    fun onView(view: T?)
}
