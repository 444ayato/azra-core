package com.example.azra_core

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeTabAdapter(fragment: Fragment, private val titles: Array<String>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = titles.size

    override fun createFragment(position: Int): Fragment {
        val fragment = TabContentFragment()
        fragment.arguments = Bundle().apply {
            putString("KATEGORI", titles[position])
        }
        return fragment
    }
}