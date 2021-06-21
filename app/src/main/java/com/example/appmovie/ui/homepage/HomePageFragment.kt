package com.example.appmovie.ui.homepage

import android.view.View
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.ui.favorite.FavoriteFragment
import com.example.appmovie.ui.genres.GenresFragment
import com.example.appmovie.ui.hotmovie.HotFragment
import com.example.appmovie.utils.ItemBottomNav
import kotlinx.android.synthetic.main.fragment_home_page.*

@Suppress("DEPRECATION")
class HomePageFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_home_page

    override fun onViewCreated(view: View) {
        onInitViewPager()
        onInitNavigation()
    }

    private fun onInitViewPager() {
        val listFragment = listOf(
            HotFragment.newInstance(),
            GenresFragment.newInstance(),
            FavoriteFragment.newInstance()
        )
        fragmentManager?.let {
            viewPagerHomePage.adapter = HomePageAdapter(it, listFragment)
        }
    }

    private fun onInitNavigation() {
        bottomNavHomePage.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHotPage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.MOVIE_HOT.ordinal
                    true
                }
                R.id.itemGenresPage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.MOVIE_GENRES.ordinal
                    true
                }
                R.id.itemFavoritePage -> {
                    viewPagerHomePage.currentItem = ItemBottomNav.MOVIE_FAVORITE.ordinal
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
