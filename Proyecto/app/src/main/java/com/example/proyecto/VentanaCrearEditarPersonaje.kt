package com.example.proyecto

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.proyecto.BBDD.AppDatabase
import com.example.proyecto.BBDD.Habilidad
import com.example.proyecto.BBDD.Personaje
import kotlinx.android.synthetic.main.activity_ventana_crear_editar_personaje.*
import java.lang.Exception
import kotlin.random.Random

class VentanaCrearEditarPersonaje : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    val lista_links_imagenes_personaje: MutableList<String> = mutableListOf(
        "https://i.gyazo.com/fbb8aa45761f13fb575c97ead5d3177c.png",
        "https://i.gyazo.com/a8587b2548ae73a408db8589523689e1.png",
        "https://i.gyazo.com/a31dfeee45e50b414b55401438fc5fd5.png",
        "https://i.gyazo.com/93a1ccb097bc9b1d6d4ce979aa9c04ba.png",
        "https://i.gyazo.com/ca9311db09cee7be6c311d92b242380a.png",
        "https://i.gyazo.com/c7abbe94c2264f064470274f20394e16.png",
        "https://i.gyazo.com/829513d67619594247694d474f4540a8.png",
        "https://i.gyazo.com/b55ced8d256e4f77d28975cc85b668dd.png",
        "https://i.gyazo.com/5a9fc97b828572b914dfbc42ea90e6b7.png",
        "https://i.gyazo.com/e8b9b5c1675037308be828b27b4b0c1b.png",
        "https://i.gyazo.com/6d3b9c596f0ab82f590d32a5761b3d73.png",
        "https://i.gyazo.com/d56140b266a2a27013b9bb019cb57631.png",
        "https://i.gyazo.com/729bdae62a158553eff878cce3e89541.png",
        "https://i.gyazo.com/dd7abb4a5288a8f9d8753e0be1c09d43.png"
    )

    val lista_links_imagenes_habilidades: MutableList<String> = mutableListOf(
        "https://i.gyazo.com/8beabd1707b5370335c7bc6d43f0ad85.png",
        "https://i.gyazo.com/1f5a5b0e8c4cfebe4855b85ba7121432.png",
        "https://i.gyazo.com/28e0d0a3215eb9f049763cd36fc779f9.png",
        "https://i.gyazo.com/7b3b5065b6d9e54d0df9d5c9495671b6.png",
        "https://i.gyazo.com/e37192bb1a304bd0c2e11653438fbec5.png",
        "https://i.gyazo.com/98d30406226b4631d1e91c07e5a3bbcd.png",
        "https://i.gyazo.com/579ee07f02804c67c8d3b819cac048a0.png",
        "https://i.gyazo.com/87018a8e6279f79e2162d2f7ba732529.png",
        "https://i.gyazo.com/91e4a406b6e7d58cfe3227f5fa526aed.png",
        "https://i.gyazo.com/8ca797a3ad99867af0d8a74efa729175.png",
        "https://i.gyazo.com/ef00c1f35ea81a9aec458223f0b51143.png"
    )

    var intCrearEditar = 0 // 0 = crear, 1 = editar
    var idPersonaje = 6
    var personajeBorrar = Personaje(-1, "", "")
    var habilidad1Borrar = Habilidad(-1, "", "", "", -1)
    var habilidad2Borrar = Habilidad(-1, "", "", "", -1)
    var habilidad3Borrar = Habilidad(-1, "", "", "", -1)
    var habilidad4Borrar = Habilidad(-1, "", "", "", -1)

    companion object {
        val ID_PERSONAJE = "ID_PERSONAJE"
        val INT_CREAREDITAR = "INT_CREAREDITAR"
        val CREAREDITAR = 1
        val ELIMINADO = "ELIMINADO"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_crear_editar_personaje)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val extras: Bundle? = intent.extras
        idPersonaje = try { extras!!.getInt(ID_PERSONAJE) } catch (e: Exception) { 6 }
        intCrearEditar = try { extras!!.getInt(INT_CREAREDITAR) } catch (e: Exception) { 0 }


        if (intCrearEditar == 1) {
            crearEditar_titulo.text = "DATOS PERSONAJE A EDITAR"
            crearEditar_b_guardar.text = "Editar"
            //cargar personaje y rellenar los datos
            var personajeEditar: Personaje?
            Thread(Runnable {
                val bd_personajes1 = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
                personajeEditar = try {
                    bd_personajes1.personajeDao().findById(idPersonaje)
                } catch (e: Exception) {
                    Personaje(-1, "NoExiste", "SinLink")
                }
                bd_personajes1.close()

                if (personajeEditar?.id is Int && personajeEditar?.id != -1) {
                    val habilidadesEditar: List<Habilidad>
                    val bd_habilidades1 = Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
                    habilidadesEditar = try {
                        bd_habilidades1.habilidadDao().getAllChampAbilities(idPersonaje)
                    } catch (e: Exception) {
                        listOf<Habilidad>()
                    }
                    bd_habilidades1.close()

                    try {
                        if (habilidadesEditar.isNotEmpty()) {
                            runOnUiThread {
                                crearEditar_et_nombre.setText(personajeEditar?.nombre)
                                crearEditar_et_linkImagen.setText(personajeEditar?.link_imagen)
                                personajeBorrar = personajeEditar!!

                                crearEditar_et_nombreHabilidad1.setText(habilidadesEditar[0].nombre)
                                crearEditar_et_descHabilidad1.setText(habilidadesEditar[0].descripcion)
                                crearEditar_et_linkHabilidad1.setText(habilidadesEditar[0].link_imagen)
                                habilidad1Borrar = habilidadesEditar[0]

                                crearEditar_et_nombreHabilidad2.setText(habilidadesEditar[1].nombre)
                                crearEditar_et_descHabilidad2.setText(habilidadesEditar[1].descripcion)
                                crearEditar_et_linkHabilidad2.setText(habilidadesEditar[1].link_imagen)
                                habilidad2Borrar = habilidadesEditar[1]

                                crearEditar_et_nombreHabilidad3.setText(habilidadesEditar[2].nombre)
                                crearEditar_et_descHabilidad3.setText(habilidadesEditar[2].descripcion)
                                crearEditar_et_linkHabilidad3.setText(habilidadesEditar[2].link_imagen)
                                habilidad3Borrar = habilidadesEditar[2]

                                crearEditar_et_nombreHabilidad4.setText(habilidadesEditar[3].nombre)
                                crearEditar_et_descHabilidad4.setText(habilidadesEditar[3].descripcion)
                                crearEditar_et_linkHabilidad4.setText(habilidadesEditar[3].link_imagen)
                                habilidad4Borrar = habilidadesEditar[3]

                                crearEditar_b_borrar.visibility = View.VISIBLE
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(
                                this,
                                "Error cargando datos del personaje",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }).start()
        }

        crearEditar_id.text = "$idPersonaje"

        crearEditar_b_borrar.setOnClickListener {
            Thread(Runnable {
                var eliminacion = true
                val bd_personaje2 = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
                try {
                    bd_personaje2.personajeDao().delete(personajeBorrar)
                    runOnUiThread {
                        Toast.makeText(this, "¡Personaje ${personajeBorrar.nombre} eliminado!", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        eliminacion = false
                        Toast.makeText(this, "Error eliminando ${personajeBorrar.nombre}", Toast.LENGTH_LONG).show()
                        Log.d("Error", e.toString())
                    }
                }
                bd_personaje2.close()
                val bd_habilidades2 =
                    Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
                var contador = 0
                val listaHabilidadesBorrar = listOf(habilidad1Borrar, habilidad2Borrar, habilidad3Borrar, habilidad4Borrar)
                for (habilidad in listaHabilidadesBorrar) {
                    contador++
                    try {
                        bd_habilidades2.habilidadDao().delete(habilidad)
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Error eliminando: Habilidad $contador", Toast.LENGTH_SHORT).show()
                            Log.d("Error", e.toString())
                        }
                    }
                }
                bd_habilidades2.close()
                runOnUiThread {
                    val resultIntent = Intent()
                    resultIntent.putExtra(ELIMINADO, eliminacion)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }).start()
        }

        crearEditar_b_guardar.setOnClickListener {

            val nombrePersonaje = crearEditar_et_nombre.text.toString()
            var linkFoto = crearEditar_et_linkImagen.text.toString()

            val nombreHabilidad1 = crearEditar_et_nombreHabilidad1.text.toString()
            val descripcionHabilidad1 = crearEditar_et_descHabilidad1.text.toString()
            var linkFotoHabilidad1 = crearEditar_et_linkHabilidad1.text.toString()

            val nombreHabilidad2 = crearEditar_et_nombreHabilidad2.text.toString()
            val descripcionHabilidad2 = crearEditar_et_descHabilidad2.text.toString()
            var linkFotoHabilidad2 = crearEditar_et_linkHabilidad2.text.toString()

            val nombreHabilidad3 = crearEditar_et_nombreHabilidad3.text.toString()
            val descripcionHabilidad3 = crearEditar_et_descHabilidad3.text.toString()
            var linkFotoHabilidad3 = crearEditar_et_linkHabilidad3.text.toString()

            val nombreHabilidad4 = crearEditar_et_nombreHabilidad4.text.toString()
            val descripcionHabilidad4 = crearEditar_et_descHabilidad4.text.toString()
            var linkFotoHabilidad4 = crearEditar_et_linkHabilidad4.text.toString()

            if (nombrePersonaje.isEmpty() || nombreHabilidad1.isEmpty() || nombreHabilidad2.isEmpty()
                || nombreHabilidad3.isEmpty() || nombreHabilidad4.isEmpty() || descripcionHabilidad1.isEmpty()
                || descripcionHabilidad2.isEmpty() || descripcionHabilidad3.isEmpty()
                || descripcionHabilidad4.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("¡Tienes que rellenar todos los datos necesarios!\n" +
                        "Los únicos opcionales son los links.")
                builder.setNegativeButton("Ok"){dialog, which ->  }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            } else {
                if (linkFoto.isEmpty()) {
                    linkFoto = lista_links_imagenes_personaje[Random.nextInt(
                        0,
                        lista_links_imagenes_personaje.size
                    )]
                }
                if (linkFotoHabilidad1.isEmpty()) {
                    linkFotoHabilidad1 = lista_links_imagenes_habilidades[Random.nextInt(
                        0,
                        lista_links_imagenes_habilidades.size
                    )]
                }
                if (linkFotoHabilidad2.isEmpty()) {
                    linkFotoHabilidad2 = lista_links_imagenes_habilidades[Random.nextInt(
                        0,
                        lista_links_imagenes_habilidades.size
                    )]
                }
                if (linkFotoHabilidad3.isEmpty()) {
                    linkFotoHabilidad3 = lista_links_imagenes_habilidades[Random.nextInt(
                        0,
                        lista_links_imagenes_habilidades.size
                    )]
                }
                if (linkFotoHabilidad4.isEmpty()) {
                    linkFotoHabilidad4 = lista_links_imagenes_habilidades[Random.nextInt(
                        0,
                        lista_links_imagenes_habilidades.size
                    )]
                }

                val personaje = Personaje(idPersonaje, nombrePersonaje, linkFoto)

                val habilidad1 = Habilidad(((idPersonaje - 1) * 4 + 1), nombreHabilidad1, descripcionHabilidad1, linkFotoHabilidad1, idPersonaje)
                val habilidad2 = Habilidad(((idPersonaje - 1) * 4 + 2), nombreHabilidad2, descripcionHabilidad2, linkFotoHabilidad2, idPersonaje)
                val habilidad3 = Habilidad(((idPersonaje - 1) * 4 + 3), nombreHabilidad3, descripcionHabilidad3, linkFotoHabilidad3, idPersonaje)
                val habilidad4 = Habilidad(((idPersonaje - 1) * 4 + 4), nombreHabilidad4, descripcionHabilidad4, linkFotoHabilidad4, idPersonaje)
                val listaHabilidades = listOf(habilidad1, habilidad2, habilidad3, habilidad4)

                Thread(Runnable {
                    val bd_personaje = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
                    try {
                        bd_personaje.personajeDao().insertAll(personaje)
                        runOnUiThread {
                            if (crearEditar_b_guardar.text == "Editar") {
                                Toast.makeText(
                                    this,
                                    "¡Personaje $nombrePersonaje editado!",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "¡Personaje $nombrePersonaje introducido!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            finish()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, "Error insertando a $nombrePersonaje", Toast.LENGTH_LONG).show()
                            Log.d("Error", e.toString())
                        }
                    }
                    bd_personaje.close()
                    val bd_habilidades =
                        Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
                    var contador = 0
                    for (habilidad in listaHabilidades) {
                        contador++
                        try {
                            bd_habilidades.habilidadDao().insertAll(habilidad)
                        } catch (e: Exception) {
                            runOnUiThread {
                                Toast.makeText(this, "Error con: Habilidad $contador", Toast.LENGTH_LONG).show()
                                Log.d("Error", e.toString())
                            }
                        }
                    }
                    bd_habilidades.close()
                }).start()
            }
        }

        crearEditar_b_volver.setOnClickListener {
            finish()
        }

    }

    fun configurar_datos() {

    }

    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    */

}
