package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_ventana_que_es_valorant.*

class VentanaQueEsValorant : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_que_es_valorant)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Linkify.addLinks(queEsValorant_link, Linkify.WEB_URLS)
        //.setText(Html.fromHtml(getResources().getString(R.string.string_with_links)));
        //queEsValorant_link.text = Html.fromHtml(resources.getString(R.string.queesvalorant_13))
        queEsValorant_link.movementMethod = LinkMovementMethod.getInstance()

        queEsValorant_volver.setOnClickListener {
            finish()
        }

        queEsValorant_ee1.setOnClickListener { easter_egg5() }
        queEsValorant_ee2.setOnClickListener { easter_egg5() }
    }

    fun easter_egg5() {
        Toast.makeText(this, "¡¡Has encontrado el quinto Easter Egg!!", Toast.LENGTH_LONG).show()
    }
}
