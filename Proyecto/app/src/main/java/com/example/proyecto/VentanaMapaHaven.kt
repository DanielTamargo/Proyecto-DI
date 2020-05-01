package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.fragment_bind.*
import kotlinx.android.synthetic.main.fragment_haven.*
import kotlinx.android.synthetic.main.fragment_split.*

class VentanaMapaHaven : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var toolbar: Toolbar
    private var num_veces_clic_checkbox = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_mapa_haven)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    */

    fun easter_egg_3() {
        num_veces_clic_checkbox ++
        if (num_veces_clic_checkbox == 9) {
            Toast.makeText(this, "¡¡Has encontrado el segundo Easter Egg!!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCLickFragmentButton(nombre_mapa: String, accion: Int) {

        //Listeners checkbox de cada mapa
        if (nombre_mapa == "haven") {
            //Haven
            if (accion == 0) {
                if (haven_checkBox_defensores.isChecked) {
                    haven_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_haven_defenders)
                } else {
                    haven_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_haven)
                }
                easter_egg_3()
            } else if (accion == 1) {
                finish()
            }
        } else if (nombre_mapa == "split") {
            //Split
            if (accion == 0) {
                if (split_checkBox_defensores.isChecked) {
                    split_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_split_defenders)
                } else {
                    split_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_split)
                }
                easter_egg_3()
            } else if (accion == 1) {
                finish()
            }
        } else if (nombre_mapa == "bind") {
            //Bind
            if (accion == 0) {
                if (bind_checkBox_defensores.isChecked) {
                    bind_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_bind_defenders)
                } else {
                    bind_minimapa.setImageResource(R.drawable.valorant_mapas_minimapas_bind)
                }
                easter_egg_3()
            } else if (accion == 1) {
                finish()
            } else if (accion == 2) {
                Toast.makeText(this, "¡¡Has encontrado el tercer Easter Egg!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClickWeaponFragmentButton(nombre_pestanya: String) {
        //nada
    }
}
