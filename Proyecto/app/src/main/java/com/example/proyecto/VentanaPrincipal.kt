package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.proyecto.Fragmentos.ContenedorCeldasFragment

class VentanaPrincipal : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_principal)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //supportActionBar?.title = "Valorant" //<- no cambiar esto, he añadido un textView en la toolbar para personalizar la fuente

        supportFragmentManager.beginTransaction().add(R.id.contenedorCeldas,
            ContenedorCeldasFragment()
        ).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_b_queEsValorant -> {
                val intent = Intent(this, VentanaQueEsValorant::class.java)
                startActivity(intent)
            }
            R.id.menu_b_legalinfo -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Derechos Reservados")
                builder.setMessage(
                    "© 2020 Riot Games, Inc. Todos los derechos reservados. League of Legends y " +
                            "Riot Games, Inc. son marcas comerciales o marcas registradas de " +
                            "Riot Games, Inc.\n\n" +
                            "Toda la información que muestra esta aplicación es información del " +
                            "juego Valorant, propiedad de Riot Games."
                )
                builder.setNegativeButton("Ok") { dialog, which -> }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun abrir_ventana_mapas() {
        val intent = Intent(this, VentanaMapaHaven::class.java)
        startActivity(intent)
    }
}
