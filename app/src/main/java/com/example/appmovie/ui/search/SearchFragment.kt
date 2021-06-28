package com.example.appmovie.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.data.source.remote.MovieRemoteDataSource
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.ui.detail.DetailMoviePageFragment
import com.example.appmovie.ui.search.adapter.SearchAdapter
import com.example.appmovie.utils.Constant
import kotlinx.android.synthetic.main.framgment_search.*
import java.lang.Exception

class SearchFragment : BaseFragment(), SearchMovieContact.View {

    private lateinit var searchMoviePresenter: SearchMoviePresenter
    private lateinit var searchAdapter: SearchAdapter

    private var page = Constant.DEFAULT_PAGE
    private var isLoading = false

    override fun getLayoutId() = R.layout.framgment_search

    override fun onViewCreated(view: View) {
        initView()
        initData()
    }

    override fun onSearchSuccess(listSearchMovie: MutableList<SearchMovie?>) {
        if (listSearchMovie.size == 0 && page == Constant.DEFAULT_PAGE) {
            Toast.makeText(
                requireContext(),
                context?.getString(R.string.no_result),
                Toast.LENGTH_SHORT
            ).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }
        } else {
            if (page == 1) {
                searchAdapter.setData(listSearchMovie)
            } else {
                searchAdapter.apply {
                    removeMoviesLastItem()
                    addMovies(listSearchMovie)
                }
                isLoading = false
            }
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(requireContext(), exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        focusEdtSearchMovie()

        imageSearchBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            hideKeyboard()
        }
        imageViewSearch.setOnClickListener {
            page = Constant.DEFAULT_PAGE
            recyclerViewSearchMovie.layoutManager?.scrollToPosition(0)
            searchMoviePresenter.getDataSearch(page, edtSearchMovie.text.toString())
            hideKeyboard()
        }
        searchAdapter = SearchAdapter {
            addFragment(DetailMoviePageFragment.newInstance(it), R.id.mainFragment)
        }
        recyclerViewSearchMovie.apply {
            adapter = searchAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val gridLayoutManager = (layoutManager as GridLayoutManager)
                    val totalItemCount = gridLayoutManager.itemCount
                    val lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (!isLoading && totalItemCount <= lastItem + Constant.VISIBLE_THRESHOLD) {
                        loadMoreData()
                        isLoading = true
                    }
                }
            })
        }
    }

    private fun initData() {
        searchMoviePresenter = SearchMoviePresenter(
            MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance()
            )
        )
        searchMoviePresenter.apply {
            onStart()
            onView(this@SearchFragment)
        }
    }

    private fun focusEdtSearchMovie() {
        edtSearchMovie.requestFocus()
        val inputMethodManager =
            context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideKeyboard() {
        activity?.let {
            val inputMethodManager =
                context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            val view = it.currentFocus ?: View(activity)
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun loadMoreData() {
        searchAdapter.addMoviesNull()
        page++
        searchMoviePresenter.getDataSearch(page, edtSearchMovie.text.toString())
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
