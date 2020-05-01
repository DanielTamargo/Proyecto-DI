package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class VentanaArmas : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_armas)

        /*
        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
         */
    }


}
