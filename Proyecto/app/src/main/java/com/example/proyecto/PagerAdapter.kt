package com.example.proyecto

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.proyecto.FragmentosMapas.BindFragment
import com.example.proyecto.FragmentosMapas.HavenFragment
import com.example.proyecto.FragmentosMapas.SplitFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HavenFragment.newInstance("Haven", R.color.softBlue)
            1 -> SplitFragment.newInstance("Split", R.color.softOrange)
            2 -> BindFragment.newInstance("Bind", R.color.smoothGray)
            else -> HavenFragment.newInstance("Haven", R.color.colorPrimary)
        }
    }

    override fun getItemCount(): Int {
        return 3 //son 3 mapas
    }

}