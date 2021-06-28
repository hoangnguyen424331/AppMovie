package com.example.appmovie.ui.detail

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.*
import com.example.appmovie.data.source.local.MovieLocalDataSource
import com.example.appmovie.data.source.remote.MovieRemoteDataSource
import com.example.appmovie.data.source.repository.FavoriteRepository
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.ui.detail.actor.ActorFragment
import com.example.appmovie.ui.detail.actor.adapter.DetailActorAdapter
import com.example.appmovie.ui.detail.adapter.RecommendAdapter
import com.example.appmovie.ui.favorite.FavoriteFragment
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.lang.Exception
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
class DetailMoviePageFragment : BaseFragment(), DetailMovieContact.View {

    private var detailMoviePresenter: DetailMoviePresenter? = null
    private var favorite: Favorite? = null
    private var idMovie: Int? = null
    private var isFavorite = false

    private val actorAdapter by lazy {
        DetailActorAdapter {
            addFragment(ActorFragment.newInstance(it), R.id.mainFragment)
        }
    }

    private val recommendAdapter by lazy {
        RecommendAdapter {
            addFragment(newInstance(it), R.id.mainFragment)
        }
    }

    override fun getLayoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMoviePresenter = DetailMoviePresenter(
            MovieRepository.getInstance(MovieRemoteDataSource.getInstance()),
            FavoriteRepository.getInstance(MovieLocalDataSource.getInstance(requireActivity()))
        )
        arguments?.let {
            idMovie = it.getInt(BUNDLE_ID_DETAIL_MOVIE)
        }
        detailMoviePresenter?.let {
            it.onView(this)
            idMovie?.let { id ->
                it.apply {
                    getMovieDetail(id)
                    getVideoMovie(id)
                    getListRecommend(id)
                    getActor(id)
                }
            }
        }
    }

    override fun onViewCreated(view: View) {
        onInitView()
        onInitRecommend()
        onInitActor()
    }

    override fun loadContentMovieOnSuccess(detailMovie: DetailMovie) {
        detailMovie.run {
            favorite = Favorite(id, title, posterUrl, tagline, voteAverage)
        }
        initDataMovieDetail(detailMovie)
    }

    override fun loadListActorOnSuccess(actors: List<Actor>) {
        actorAdapter.setData(actors)
    }

    override fun loadRecommendOnSuccess(movies: List<HotMovie>) {
        recommendAdapter.setData(movies)
    }

    override fun loadVideoOnSuccess(video: VideoMovie?) {
        imagePlay.setOnClickListener {
            video?.let {
                openYoutube(it.key)
            } ?: Toast.makeText(context, getString(R.string.no_video), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(activity, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun onInitView() {
        val height = requireActivity().windowManager.defaultDisplay.run {
            val size = Point()
            getSize(size)
            size.y
        }

        constraintLayoutBar.apply {
            maxHeight = (height * 0.31).roundToInt()
            minHeight = (height * 0.31).roundToInt()
        }
    }

    private fun onInitRecommend() {
        recyclerViewRecommend.apply {
            setHasFixedSize(true)
            adapter = recommendAdapter
        }
    }

    private fun onInitActor() {
        recyclerViewActor.apply {
            setHasFixedSize(true)
            adapter = actorAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initDataMovieDetail(detailMovie: DetailMovie) {
        detailMovie.apply {
            textTitle.text = title
            textOverView.text = "Overview: $overview"
            textRelease.text = releaseDate
            textTagLine.text = tagline
            textGenres.text = detailMovie.genres.joinToString(", ") {
                it.name.toString()
            }
            ratingBar.rating = voteAverage.toFloat() / 2
            imagePoster.loadFromUrl(Constant.BASE_URL_IMAGE + posterUrl)
            imageBackground.loadFromUrl(Constant.BASE_URL_IMAGE + backdropUrl)
        }
    }

    private fun openYoutube(idYoutube: String?) {
        try {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_APP + idYoutube)
                )
            )
        } catch (e: ActivityNotFoundException) {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_WEB + idYoutube)
                )
            )
        }
    }

    override fun onEvent() {
        imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        imageFavorite.setOnClickListener {
            favorite?.let {
                updateFavorite(it)
                FavoriteFragment.isCheckFavorite = !FavoriteFragment.isCheckFavorite
            }
        }
    }

    private fun updateFavorite(favorite: Favorite) {
        detailMoviePresenter?.let {
            if (isFavorite) it.deleteFavorite(favorite.id)
            else it.insertFavorite(favorite)
            isFavorite = !isFavorite
            selectedFavorite()
        }
    }

    private fun selectedFavorite() {
        if (isFavorite) imageFavorite.setImageResource(R.drawable.ic_heart_red)
        else imageFavorite.setImageResource(R.drawable.ic_heart_default)
    }

    companion object {
        private const val BUNDLE_ID_DETAIL_MOVIE = "BUNDLE_ID_DETAIL_MOVIE"
        private const val URI_YOUTUBE_WEB = "http://www.youtube.com/watch?v="
        private const val URI_YOUTUBE_APP = "vnd.youtube:"

        fun newInstance(id: Int) = DetailMoviePageFragment().apply {
            arguments = bundleOf(BUNDLE_ID_DETAIL_MOVIE to id)
        }
    }
}
