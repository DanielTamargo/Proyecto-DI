package com.example.proyecto.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.proyecto.Fragmentos.WeaponPagerAdapter

import com.example.proyecto.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class WeaponViewPagerFragment : Fragment() {

    private lateinit var adaptadorPager: WeaponPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weapon_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adaptadorPager = WeaponPagerAdapter(this)
        viewPager = view.findViewById(R.id.pager_weapon)
        viewPager.adapter = adaptadorPager
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                1 -> "Pistolas"
                2 -> "Asalto"
                3 -> "Rifles"
                else -> "Todas"
            }
        }.attach()
    }

}
