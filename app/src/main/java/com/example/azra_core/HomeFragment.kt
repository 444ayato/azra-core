package com.example.azra_core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val labelTab = arrayOf("Program Aktif", "Rencana Kerja")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutHome)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerHome)

        val adapter = HomeTabAdapter(this, labelTab)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = labelTab[position]
        }.attach()
    }
}