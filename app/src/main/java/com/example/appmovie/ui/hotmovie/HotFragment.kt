package com.example.appmovie.ui.hotmovie

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.MovieRemoteDataSource
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.ui.detail.DetailMoviePageFragment
import com.example.appmovie.ui.hotmovie.adapter.HotMovieAdapter
import com.example.appmovie.utils.Constant
import com.example.appmovie.utils.HotMovieType
import kotlinx.android.synthetic.main.fragment_hot_movie.*

class HotFragment : BaseFragment(), HotMovieContact.View {

    private lateinit var hotMoviePresenter: HotMoviePresenter
    private var page = Constant.DEFAULT_PAGE
    private var hotMovieType = HotMovieType.POPULAR
    private var isLoading = false
    private val adapterHotMovie by lazy {
        HotMovieAdapter {
            it.id?.apply {
                addFragment(DetailMoviePageFragment.newInstance(this), R.id.mainFragment)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_hot_movie

    override fun onViewCreated(view: View) {
        initView()
        initData()
    }

    override fun onGetMoviesSuccess(movies: MutableList<HotMovie?>) {
        if (page == Constant.DEFAULT_PAGE) {
            adapterHotMovie.setData(movies)
        } else {
            adapterHotMovie.apply {
                removeMoviesLastItem()
                addMovies(movies)
            }
            isLoading = false
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(activity, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        initRecyclerView()
        setOnClickButton()
        initSwipeRefresh()
    }

    private fun initData() {
        hotMoviePresenter = HotMoviePresenter(
            MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance()
            )
        )
        hotMoviePresenter.apply {
            onView(this@HotFragment)
            onStart()
        }
    }

    private fun initRecyclerView() {
        recyclerViewHotMovie.apply {
            setHasFixedSize(true)
            adapter = adapterHotMovie
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager =
                        recyclerView.layoutManager as LinearLayoutManager?
                    val totalItemCount = linearLayoutManager?.itemCount?.minus(1)
                    val lastVisibleItem =
                        linearLayoutManager?.findLastCompletelyVisibleItemPosition()
                    if (!isLoading && totalItemCount == lastVisibleItem) {
                        loadMoreData()
                        isLoading = true
                    }
                }
            })
        }
    }

    private fun setOnClickButton() {
        btnPopular.setOnClickListener {
            changeDataMovie(HotMovieType.POPULAR)
            setButtonClick(btnPopular)
            setButtonNotClick(btnTopRate)
            setButtonNotClick(btnUpComing)
        }
        btnTopRate.setOnClickListener {
            changeDataMovie(HotMovieType.TOP_RATE)
            setButtonClick(btnTopRate)
            setButtonNotClick(btnPopular)
            setButtonNotClick(btnUpComing)
        }
        btnUpComing.setOnClickListener {
            changeDataMovie(HotMovieType.UP_COMING)
            setButtonClick(btnUpComing)
            setButtonNotClick(btnPopular)
            setButtonNotClick(btnTopRate)
        }
    }

    private fun initSwipeRefresh() {
        swipeRefreshHotMovie.setOnRefreshListener {
            changeDataMovie(hotMovieType)
        }
    }

    private fun loadMoreData() {
        adapterHotMovie.addMoviesNull()
        page++
        hotMoviePresenter.getHotMovies(page, hotMovieType)
    }

    private fun changeDataMovie(typeHotMovie: HotMovieType) {
        recyclerViewHotMovie.layoutManager?.scrollToPosition(0)
        page = Constant.DEFAULT_PAGE
        hotMovieType = typeHotMovie
        hotMoviePresenter.getHotMovies(page, hotMovieType)
    }

    private fun setButtonClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private fun setButtonNotClick(button: Button) {
        button.apply {
            background = ResourcesCompat.getDrawable(
                resources, R.drawable.custom_hot_screen_button_not_click, null
            )
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    companion object {
        fun newInstance() = HotFragment()
    }
}
