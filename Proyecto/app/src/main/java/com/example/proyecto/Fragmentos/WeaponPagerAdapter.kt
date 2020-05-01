package com.example.proyecto.Fragmentos

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.proyecto.FragmentosArmas.AsaltoFragment
import com.example.proyecto.FragmentosArmas.PistolasFragment
import com.example.proyecto.FragmentosArmas.RiflesFragment
import com.example.proyecto.FragmentosArmas.TodasLasArmasFragment
import com.example.proyecto.R

class WeaponPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> PistolasFragment.newInstance("Pistolas", R.color.softBlue)
            2 -> AsaltoFragment.newInstance("Asalto", R.color.softOrange)
            3 -> RiflesFragment.newInstance("Rifles", R.color.smoothGray)
            else -> TodasLasArmasFragment.newInstance("Todas", R.color.colorPrimary)
        }
    }

    override fun getItemCount(): Int {
        return 4 //todas, pistolas, asalto, rifles
    }
}