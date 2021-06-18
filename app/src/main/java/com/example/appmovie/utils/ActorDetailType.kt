package com.example.appmovie.utils

enum class ActorDetailType(val path: String) {
    ACTOR("/credits?"),
    EXTERNAL("/external_ids?")
}