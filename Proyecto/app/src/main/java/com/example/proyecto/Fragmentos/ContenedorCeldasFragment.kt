package com.example.proyecto.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.Adaptador
import com.example.proyecto.R

/**
 * A simple [Fragment] subclass.
 */
class ContenedorCeldasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_contenedor_celdas, container, false).apply{}
        recyclerView = rootView.findViewById(R.id.contenedorCeldas)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = Adaptador()
        return rootView
    }

}
