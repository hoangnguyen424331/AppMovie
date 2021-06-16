package com.example.appmovie.data.model

data class Actor(
    val id: Int?,
    val name: String?,
    val imageUrl: String?
)

object ActorEntry {
    const val ACTORS = "cast"
    const val ID = "id"
    const val NAME = "name"
    const val IMAGE_URL = "profile_path"
}
