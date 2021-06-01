package com.example.appmovie.ui.main

import com.example.appmovie.R
import com.example.appmovie.base.BaseActivity
import com.example.appmovie.ui.homepage.HomePageFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun onEvent() {}

    override fun onInit() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragment, HomePageFragment.newInstance())
            .commit()
    }
}
