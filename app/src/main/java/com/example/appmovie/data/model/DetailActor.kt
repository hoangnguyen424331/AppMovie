package com.example.appmovie.data.model

data class DetailActor(
    val id: Int?,
    val name: String?,
    val imageUrl: String?,
    val birthday: String?,
    val gender: Int?,
    val address: String?,
    val biography: String?
)

object DetailActorEntry {
    const val ID = "id"
    const val NAME = "name"
    const val IMAGE_URL = "name"
    const val BIRTHDAY = "birthday"
    const val GENDER = "gender"
    const val ADDRESS = "place_of_birth"
    const val BIOGRAPHY = "biography"
}
