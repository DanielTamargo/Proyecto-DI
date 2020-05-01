package com.example.proyecto.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.proyecto.PagerAdapter
import com.example.proyecto.R

/**
 * A simple [Fragment] subclass.
 */
class ViewPagerFragment : Fragment() {

    private lateinit var adaptadorPager: PagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adaptadorPager = PagerAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = adaptadorPager
    }

}
