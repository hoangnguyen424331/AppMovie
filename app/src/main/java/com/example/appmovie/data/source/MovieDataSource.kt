package com.example.appmovie.data.source

import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.utils.HotMovieType

interface MovieDataSource {

    interface Remote {
        fun getHotMoves(
            page: Int,
            hotMovieType: HotMovieType,
            listener: OnFetchDataJsonListener<MutableList<HotMovie?>>
        )
    }
}
