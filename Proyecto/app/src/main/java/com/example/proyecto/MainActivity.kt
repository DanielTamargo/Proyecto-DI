package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.room.Room
import com.example.proyecto.BBDD.AppDatabase
import com.example.proyecto.BBDD.Habilidad
import com.example.proyecto.BBDD.Personaje
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var handler: Handler
    private lateinit var listaPersonajes: List<Personaje>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        handler = Handler()
        handler.postDelayed({
            var intento = Intent(this, VentanaPrincipal::class.java)
            startActivity(intento)
            finish()
        }, 3500) //3500 milliseconds
    }

    fun init() {
        progressBar = findViewById(R.id.progressBar)
    }
}
