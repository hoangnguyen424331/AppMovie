package com.example.appmovie.ui.detail.actor

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.DetailActor
import com.example.appmovie.data.model.External
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.MovieRemoteDataSource
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.extensions.loadFromUrl
import com.example.appmovie.extensions.toGone
import com.example.appmovie.extensions.toVisible
import com.example.appmovie.ui.detail.DetailMoviePageFragment
import com.example.appmovie.ui.detail.adapter.RecommendAdapter
import com.example.appmovie.utils.Constant
import com.example.appmovie.utils.Gender
import kotlinx.android.synthetic.main.fragment_actor.*
import java.lang.Exception

class ActorFragment : BaseFragment(), ActorContact.View {

    private lateinit var actorPresenter: ActorPresenter
    private var idActor: Int? = null

    private val movieAdapter by lazy {
        RecommendAdapter {
            addFragment(DetailMoviePageFragment.newInstance(it), R.id.mainFragment)
        }
    }

    override fun getLayoutId() = R.layout.fragment_actor

    override fun onViewCreated(view: View) {
        onInitMovies()

        actorPresenter = ActorPresenter(
            MovieRepository.getInstance(MovieRemoteDataSource.getInstance())
        )
        arguments?.let {
            idActor = it.getInt(BUNDLE_ID_DETAIL_ACTOR)
        }
        actorPresenter.run {
            idActor?.let {
                onView(this@ActorFragment)
                getActorDetail(it)
                getExternal(it)
                getListMovieOfActor(it)
            }
        }
    }

    override fun onEvent() {
        imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun loadContentActorOnSuccess(detailActor: DetailActor) {
        initDataActorDetail(detailActor)
    }

    override fun loadContentExternalOnSuccess(external: External) {
        external.run {
            facebook?.let {
                if (it.isEmpty())
                    imageFacebook.toVisible()
                else
                    imageFacebook.toGone()
            } ?: imageFacebook.toGone()

            instagram?.let {
                if (it.isEmpty())
                    imageInstagram.toVisible()
                else
                    imageInstagram.toGone()
            } ?: imageInstagram.toGone()

            twitter?.let {
                if (it.isEmpty())
                    imageTwitter.toVisible()
                else
                    imageTwitter.toGone()
            } ?: imageTwitter.toGone()

            imageFacebook.setOnClickListener {
                facebook?.let { fb ->
                    openExternal(Constant.URL_FACEBOOK + fb)
                }
            }
            imageInstagram.setOnClickListener {
                instagram?.let { ig ->
                    openExternal(Constant.URL_INSTAGRAM + ig)
                }
            }
            imageTwitter.setOnClickListener {
                twitter?.let { twt ->
                    openExternal(Constant.URL_TWITTER + twt)
                }
            }
        }
    }

    override fun loadMoviesOnSuccess(movies: List<HotMovie>) {
        movieAdapter.setData(movies)
    }

    override fun onError(exception: Exception?) {
        exception?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initDataActorDetail(detailActor: DetailActor) {
        resources.apply {
            detailActor.run {
                name?.let {
                    textNameActor.text = it
                }
                birthday?.let {
                    textBirthday.text = formatHTML(getString(R.string.birthday), it)
                }
                address?.let {
                    textAddress.text = formatHTML(getString(R.string.place_of_birth), it)
                }
                gender?.let {
                    textGender.text =
                        formatHTML(getString(R.string.gender), Gender.values()[it].name)
                }
                biography?.let {
                    textBiography.text = formatHTML(getString(R.string.biography), it)
                }
                imageUrl?.let {
                    imagePosterActor.loadFromUrl(Constant.BASE_URL_IMAGE + it)
                }
            }
        }
    }

    private fun onInitMovies() {
        recyclerViewKnownFor.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun openExternal(nameExternal: String) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(nameExternal))
            startActivity(browserIntent)
        } catch (exception: Exception) {
            onError(exception)
        }
    }

    private fun formatHTML(title: String, info: String) =
        HtmlCompat.fromHtml("<B>$title</B><BR> $info", HtmlCompat.FROM_HTML_MODE_LEGACY)

    companion object {
        private const val BUNDLE_ID_DETAIL_ACTOR = "BUNDLE_ID_DETAIL_ACTOR"

        fun newInstance(id: Int) = ActorFragment().apply {
            arguments = bundleOf(BUNDLE_ID_DETAIL_ACTOR to id)
        }
    }
}
