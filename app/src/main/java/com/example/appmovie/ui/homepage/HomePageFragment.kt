package com.example.appmovie.ui.homepage

import android.view.View
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment

class HomePageFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_home_page

    override fun onViewCreated(view: View) {
        onInitViewPager()
        onClick()
        onInitNavigation()
    }

    private fun onInitViewPager() {}

    private fun onClick() {}

    private fun onInitNavigation() {}

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
