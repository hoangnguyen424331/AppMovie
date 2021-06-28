package com.example.appmovie.ui.favorite

import android.view.View
import android.widget.Toast
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.Favorite
import com.example.appmovie.data.source.local.MovieLocalDataSource
import com.example.appmovie.data.source.repository.FavoriteRepository
import com.example.appmovie.extensions.addFragment
import com.example.appmovie.ui.detail.DetailMoviePageFragment
import com.example.appmovie.ui.favorite.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.lang.Exception

class FavoriteFragment : BaseFragment(), FavoriteContact.View {

    private var favoritePresenter: FavoritePresenter? = null

    private val favoriteAdapter by lazy {
        FavoriteAdapter(
            {
                addFragment(DetailMoviePageFragment.newInstance(it), R.id.mainFragment)
            }, { id, position -> removeFavorite(id, position) }
        )
    }

    override fun getLayoutId() = R.layout.fragment_favorite

    override fun onViewCreated(view: View) {
        initView()
        initData()
    }

    override fun loadFavoriteOnSuccess(movies: MutableList<Favorite>) {
        favoriteAdapter.setData(movies)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(activity, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        recyclerViewFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun initData() {
        favoritePresenter = FavoritePresenter(
            FavoriteRepository.getInstance(MovieLocalDataSource.getInstance(requireContext()))
        )
        favoritePresenter?.apply {
            onStart()
            onView(this@FavoriteFragment)
        }
        requireActivity().supportFragmentManager.addOnBackStackChangedListener {
            if (isCheckFavorite) {
                favoritePresenter?.onStart()
                isCheckFavorite = false
            }
        }
    }

    private fun removeFavorite(id: Int, position: Int) {
        favoritePresenter?.deleteFavorite(id)
        favoriteAdapter.removeItem(position)
    }

    companion object {
        fun newInstance() = FavoriteFragment()

        var isCheckFavorite = false
    }
}
