package com.example.appmovie.ui.genres

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.Genres
import com.example.appmovie.data.model.GenresMovie
import com.example.appmovie.data.source.remote.MovieRemoteDataSource
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.ui.detail.movie.DetailMoviePageFragment
import com.example.appmovie.ui.genres.adapter.GenresAdapter
import com.example.appmovie.ui.genres.adapter.GenresMovieAdapter
import com.example.appmovie.ui.genres.adapter.GenresSelectedAdapter
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.fragment_genres_movie.*
import java.lang.Exception

class GenresFragment : BaseFragment(), GenresContact.View {

    private lateinit var genresPresenter: GenresPresenter
    private lateinit var adapterGenres: GenresAdapter
    private lateinit var adapterGenresSelected: GenresSelectedAdapter
    private lateinit var adapterGenresMovie: GenresMovieAdapter

    private var listSelectedGenres = mutableListOf<Genres?>()
    private var page = Constant.DEFAULT_PAGE
    private var isLoading = false

    private val onClickGenres = fun(genres: Genres, position: Int) {
        onClickGenres(genres, position)
    }

    private val onClickSelectedGenres = fun(positionSelected: Int?, positionRemove: Int) {
        onClickSelectedGenres(positionSelected, positionRemove)
    }

    override fun getLayoutId() = R.layout.fragment_genres_movie

    override fun onViewCreated(view: View) {
        initView()
        initData()
    }

    override fun onGetGenresSuccess(listGenres: MutableList<Genres?>) {
        listGenres[0]?.let {
            it.selected = true
            it.positionSelected = 0
        }
        adapterGenres.setData(listGenres)
        listSelectedGenres.add(listGenres[0])
        adapterGenresSelected.setData(listSelectedGenres)
        genresPresenter.getGenresMovie(Constant.DEFAULT_PAGE, listGenres[0]?.id.toString())
    }

    override fun onGetGenresMovieSuccess(listGenresMovie: MutableList<GenresMovie?>) {
        if (page == 1) {
            adapterGenresMovie.setData(listGenresMovie)
        } else {
            adapterGenresMovie.apply {
                removeMoviesLastItem()
                addMovies(listGenresMovie)
            }
            isLoading = false
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(activity, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        adapterGenres = GenresAdapter(requireContext(), onClickGenres)
        adapterGenresSelected = GenresSelectedAdapter(onClickSelectedGenres)
        adapterGenresMovie = GenresMovieAdapter {
            addFragment(DetailMoviePageFragment.newInstance(it), R.id.mainFragment)
        }

        recyclerViewGenres.adapter = adapterGenres
        recyclerViewSelected.adapter = adapterGenresSelected
        recyclerViewGenresMovie.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterGenresMovie

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
                    val totalItemCount = gridLayoutManager.itemCount.minus(2)
                    val lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (!isLoading && totalItemCount == lastItem) {
                        loadMore()
                        isLoading = true
                    }
                }
            })
        }
    }

    private fun initData() {
        genresPresenter = GenresPresenter(
            MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance()
            )
        )
        genresPresenter.apply {
            onView(this@GenresFragment)
            onStart()
        }
    }

    private fun loadMore() {
        adapterGenresMovie.addMoviesNull()
        page++
        genresPresenter.getGenresMovie(page, getQueryGenres())
    }

    private fun getQueryGenres(): String {
        var query = ""
        listSelectedGenres.forEach { genres ->
            query += ", ${genres?.id}"
        }
        return query
    }

    private fun onClickGenres(genres: Genres, position: Int) {
        page = Constant.DEFAULT_PAGE
        recyclerViewGenres.layoutManager?.scrollToPosition(0)
        adapterGenres.selectedGenres(position)
        genres.positionSelected = position
        listSelectedGenres.add(genres)
        adapterGenres.setData(listSelectedGenres)
        genresPresenter.getGenresMovie(page, getQueryGenres())
    }

    private fun onClickSelectedGenres(positionSelected: Int?, positionRemove: Int) {
        page = Constant.DEFAULT_PAGE
        recyclerViewGenresMovie.layoutManager?.scrollToPosition(0)
        adapterGenres.removeGenres(positionSelected)
        adapterGenresSelected.removeData(positionRemove)
        genresPresenter.getGenresMovie(page, getQueryGenres())
    }

    companion object {
        fun newInstance() = GenresFragment()
    }
}
