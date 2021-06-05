@file:Suppress("DEPRECATION")

package com.example.appmovie.ui.homepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class HomePageAdapter(fragmentManager: FragmentManager, private var fragment: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = fragment.size

    override fun getItem(position: Int) = fragment[position]
}
