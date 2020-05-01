package com.example.proyecto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.proyecto.BBDD.AppDatabase
import com.example.proyecto.BBDD.Habilidad
import com.example.proyecto.BBDD.Personaje
import kotlinx.android.synthetic.main.activity_testing_bbdd.*
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*

class TestingBBDD : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_bbdd)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        test_b_buscar.setOnClickListener {
            buscar_personaje()
        }

        test_b_todasHabilidades.setOnClickListener {
            mostrar_todas_habilidades()
        }

        guardar_personajes_base()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun guardar_personajes_base() {
        guardar_omen() //id: 1, ids habilidades: 1, 2, 3, 4
        guardar_sage() //id: 2, ids habilidades: 5, 6, 7, 8
        guardar_phoenix() //id: 3, ids habilidades: 9, 10, 11, 12
        guardar_raze() //id 4, ids habilidades: 13, 14, 15, 16
        guardar_jett() //id 5, ids habilidades: 17, 18, 19, 20
    }

    fun guardar_jett() {
        val jett = Personaje(5, "Jett", "https://i.gyazo.com/c24b625de1fe5de1de9e27612e7ad0f1.png")
        val jett_1 = Habilidad(
            17,
            "Borrasca",
            "Jett lanza una nube de niebla que bloquea la visión. El lanzamiento de la habilidad es muy rápido y sirve para bloquear ciertas zonas de visión aunque esta nube dura muy poco tiempo.",
            "https://i.gyazo.com/abbfe74804618f180c892aa4bf0e0b4c.png",
            5
        )
        val jett_2 = Habilidad(
            18,
            "Vendaval",
            "Jett salta y se propulsa hacia arriba. Aprovechando su pasiva, Jett puede usar esta habilidad para saltar y seguido planear para llegar a algún punto estratégico como colocarse encima de algunas cajas y aprovechar la visión, o entrar por ventanas encadenando las dos cargas de vendaval y así pillar al enemigo desprevenido. El salto hace ruido, por lo que los enemigos cercanos podrían imaginarse cuáles serán los movimientos de Jett.",
            "https://i.gyazo.com/cc9d0616ba8591df716458c4c2dd22ff.png",
            5
        )
        val jett_3 = Habilidad(
            19,
            "Viento de Cola",
            "Jett se impulsa de un salto una corta distancia hacia la dirección en la que se esté moviendo. Básicamente, se trata de un dash ofensivo o defensivo, el cual puede sorprender a cualquier enemigo.",
            "https://i.gyazo.com/206a6c3cc56fa71e90b10bea7601f95c.png",
            5
        )
        val jett_4 = Habilidad(
            20,
            "Tormenta de Cuchillas",
            "Jett se arma con siete poderosos cuchillos arrojadizos que cada uno hace 50 de daño o mata al asestar un golpe en la cabeza. Matar a un enemigo restaura todas las dagas. El clic izquierdo lanza una daga, y el clic derecho lanzará todas las dagas en un único disparo.",
            "https://i.gyazo.com/53a500418a72bf97a640516cf9ebf8c9.png",
            5
        )

        val habilidades = listOf(jett_1, jett_2, jett_3, jett_4)

        /*
        for (habilidad in habilidades) {
            Log.d("Habilidad: ", habilidad.nombre)
        }
        */

        Thread(Runnable {
            val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
            try {
                bd.personajeDao().insertAll(jett)
                runOnUiThread {
                    Toast.makeText(this, "¡Personaje Jett introducido!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Error con: Jett ya existe", Toast.LENGTH_SHORT).show()
                }
            }
            bd.close()
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            var contador = 0
            var alguna_insercion = false
            for (habilidad in habilidades) {
                contador++
                try {
                    bd_habilidades.habilidadDao().insertAll(habilidad)
                    alguna_insercion = true
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Error con: Jett - Habilidad $contador", Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                }
            }
            if (alguna_insercion) {
                runOnUiThread {
                    Toast.makeText(this, "¡Habilidades de Jett introducidas!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            bd_habilidades.close()
        }).start()

    }

    fun guardar_raze() {
        val raze = Personaje(4, "Raze", "https://i.gyazo.com/f2a55ec3cbe042a1d2c9b87bb406ee9e.png")
        val raze_1 = Habilidad(
            13,
            "Bot Explosivo",
            "Raze equipa un bot explosivo y lo lanza hacia delante en línea recta por el terreno. Este bot rebota en las paredes. Si encuentra un enemigo, lo fijará y lo perseguirá, si lo alcanza, explotará e inflingirá una gran cantidad de daño.",
            "https://i.gyazo.com/342e61eca09ada22cfdaa7588bfc362e.png",
            4
        )
        val raze_2 = Habilidad(
            14,
            "Fardo Explosiva",
            "Raze lanza instantáneamente un paquete explosivo que se adhiere a las superficies. Si reutiliza la habilidad, o al cabo de unos pocos segundos, este dispositivo explotará haciendo desencadenar una pequeña explosión que dañará a todos los demás jugadores o hará que Raze rebote. Es una habilidad ideal para romper el muro de Sage o reposicionar a Raze.",
            "https://i.gyazo.com/22a87de19ed3237b00a813a20ec8506f.png",
            4
        )
        val raze_3 = Habilidad(
            15,
            "Balas de Pintura",
            "Raze equipa una granada en racimo y la lanza, al cabo de pocos segundos después de su lanzamiento explota una primera vez, dicha explosión genera 4 subgranadas que explotan al caer al suelo y haciendo de nuevo daño. Es una habilidad ideal para castigar a los jugadores que se encierren en esquinas buscando jugar táctico.",
            "https://i.gyazo.com/49e2d679dc948278648230f1c3733b31.png",
            4
        )
        val raze_4 = Habilidad(
            16,
            "Cierratelones",
            "Raze agarra su lanzacohetes y dispara un cohete que inflinge daño en un área. Esta habilidad castiga a los enemigos cuando se agrupan o se esconden. Al disparar, Raze da un pequeño salto hacia atrás por la propia potencia del disparo.",
            "https://i.gyazo.com/e45a40e0f97a8e35a744357a5620a9c4.png",
            4
        )

        val habilidades = listOf(raze_1, raze_2, raze_3, raze_4)

        /*
        for (habilidad in habilidades) {
            Log.d("Habilidad: ", habilidad.nombre)
        }
        */

        Thread(Runnable {
            val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
            try {
                bd.personajeDao().insertAll(raze)
                runOnUiThread {
                    Toast.makeText(this, "¡Personaje Raze introducido!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Error con: Raze ya existe", Toast.LENGTH_SHORT).show()
                }
            }
            bd.close()
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            var contador = 0
            var alguna_insercion = false
            for (habilidad in habilidades) {
                contador++
                try {
                    bd_habilidades.habilidadDao().insertAll(habilidad)
                    alguna_insercion = true
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Error con: Raze - Habilidad $contador", Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                }
            }
            if (alguna_insercion) {
                runOnUiThread {
                    Toast.makeText(this, "¡Habilidades de Raze introducidas!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            bd_habilidades.close()
        }).start()

    }

    fun guardar_phoenix() {
        val phoenix = Personaje(3, "Phoenix", "https://i.gyazo.com/55ad122c01e912dc6d2f5fa60d525178.png")
        val phoenix_1 = Habilidad(
            9,
            "Llamarada",
            "Phoenix lanza una línea de fuego que levantará un muro de fuego. Los jugadores que estén en contacto con el muro perderán vida mientras sigan en contacto, en cambio, Phoenix se curará. Esta habilidad sirve para levantar un muro de fuego y de esta forma denegar visión a los enemigos. Si Phoenix gira mientras lanza la habilidad, podrá curvar ligeramente el muro.",
            "https://i.gyazo.com/d6f48c6c4133477b5f6f69759e496fc0.png",
            3
        )
        val phoenix_2 = Habilidad(
            10,
            "Bola Curva",
            "Phoenix lanza una bola curva que explotará cegando a todos los jugadores que estén en el rango de dicha explosión. Es una habilidad ideal para entrar frenar a los enemigos o irrumpir atacando.",
            "https://i.gyazo.com/a5c5e5e7f697bb55a4f4f912477f0aa3.png",
            3
        )
        val phoenix_3 = Habilidad(
            11,
            "Manos Calientes",
            "Phoenix carga el fuego en su mano y lo arroja a una zona, tras impactar con el suelo explota dejando una zona circular de fuego que dañará a los jugadores o curará al propio Phoenix.",
            "https://i.gyazo.com/3198a656a39e986558a71e5da17e8f19.png",
            3
        )
        val phoenix_4 = Habilidad(
            12,
            "Vuelta a Empezar",
            "Tras una breve canalización, Phoenix, genera un aura de fuego y tras unos cuantos segundos o al morir, haciendo honor a su nombre, muere y resurge de sus cenizas en el lugar donde tiró esta habilidad. Es una habilidad ideal para los momentos clave o para intentar abrir un hueco en las defensas enemigas. Pero mucho cuidado, cuando resurge de sus cenizas necesitará 2 segundos para prepararse, y durante este tiempo, Phoenix es totalmente vulnerable.",
            "https://i.gyazo.com/3aa84e26d5d2a4e8e2965449208ecaaa.png",
            3
        )

        val habilidades = listOf(phoenix_1, phoenix_2, phoenix_3, phoenix_4)

        /*
        for (habilidad in habilidades) {
            Log.d("Habilidad: ", habilidad.nombre)
        }
        */

        Thread(Runnable {
            val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
            try {
                bd.personajeDao().insertAll(phoenix)
                runOnUiThread {
                    Toast.makeText(this, "¡Personaje Phoenix introducido!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Error con: Phoenix ya existe", Toast.LENGTH_SHORT).show()
                }
            }
            bd.close()
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            var contador = 0
            var alguna_insercion = false
            for (habilidad in habilidades) {
                contador++
                try {
                    bd_habilidades.habilidadDao().insertAll(habilidad)
                    alguna_insercion = true
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Error con: Phoenix - Habilidad $contador", Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                }
            }
            if (alguna_insercion) {
                runOnUiThread {
                    Toast.makeText(this, "¡Habilidades de Phoenix introducidas!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            bd_habilidades.close()
        }).start()

    }

    fun guardar_sage() {
        val sage = Personaje(2, "Sage", "https://i.gyazo.com/5d4fe9542083e689ec0dd70f9091aadb.png")
        val sage_1 = Habilidad(
            5,
            "Orbe barrera",
            "Sage coloca una barrera que impedirá el paso a los jugadores. Si Sage la coloca debajo de sí misma o debajo de algún jugador, esta levantará al jugador. Por lo tanto, con esta habilidad Sage puede tanto bloquear algunas entradas o salidas como utilizar la barrera para proporcionar altitud a los jugadores, para ver desde distintos ángulos, aprovechar headglitches, o conseguir acceder a zonas a las que no se podría acceder saltando.",
            "https://i.gyazo.com/eefac8bebd2119fdf12b32a50b18dce1.png",
            2
        )
        val sage_2 = Habilidad(
            6,
            "Orbe ralentizador",
            "Sage lanza un orbe que al caer al suelo explotará congelando una zona. Todos los jugadores que caminen o salten sobre ella quedarán ralentizados. Si el jugador no camina sigilosamente, los pasos sobre hielo harán mucho ruido. Es una habilidad muy útil para frenar a los enemigos, o controlar ciertas zonas críticas.",
            "https://i.gyazo.com/09f0eb351a606caef3e4b4ad084a3759.png",
            2
        )
        val sage_3 = Habilidad(
            7,
            "Orbe de sanación",
            "Sage se cura a sí misma o a un aliado, subiendo la vida hasta los 100 puntos. Obviamente, no regenera el escudo.",
            "https://i.gyazo.com/44a612371d079358fad2a2b4c79af44c.png",
            2
        )
        val sage_4 = Habilidad(
            8,
            "Resurrección",
            "Sage revive a un aliado caído que esté cerca. Hay que usar esta habilidad con cuidado, puesto que el aliado será fácilmente rematable si es revivido en zonas donde estén los enemigos.",
            "https://i.gyazo.com/7ef486f8447da06eb367b0b152777422.png",
            2
        )

        val habilidades = listOf(sage_1, sage_2, sage_3, sage_4)

         /*
         for (habilidad in habilidades) {
             Log.d("Habilidad: ", habilidad.nombre)
         }
         */

        Thread(Runnable {
            val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
            try {
                bd.personajeDao().insertAll(sage)
                runOnUiThread {
                    Toast.makeText(this, "¡Personaje Sage introducido!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Error con: Sage ya existe", Toast.LENGTH_SHORT).show()
                }
            }
            bd.close()
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            var contador = 0
            var alguna_insercion = false
            for (habilidad in habilidades) {
                contador++
                try {
                    bd_habilidades.habilidadDao().insertAll(habilidad)
                    alguna_insercion = true
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Error con: Sage - Habilidad $contador", Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                }
            }
            if (alguna_insercion) {
                runOnUiThread {
                    Toast.makeText(this, "¡Habilidades de Sage introducidas!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            bd_habilidades.close()
        }).start()

    }

    fun guardar_omen() {
        val omen = Personaje(1, "Omen", "https://i.gyazo.com/3d47addf89c6214d1e129e2b603a6db5.png")
        val omen_1 = Habilidad(
            1,
            "Aparición Tenebrosa",
            "Omen se teleporta a la ubicación cercana indicada. Durante el casteo de esta habilidad Omen podrá sufrir daños. La habilidad emite un sonido que se puede escuchar con facilidad, por lo que puede ser detectable o usarse para engañar a los enemigos.",
            "https://i.gyazo.com/2ba55571bf3d369fa5875f6083785d8a.png",
            1
        )
        val omen_2 = Habilidad(
            2,
            "Paranoia",
            "Omen arroja un proyectil en línea recta, los jugadores impactados perderán visión y la capacidad de escuchar correctamente, como si estuvieran dentro de una pesadilla",
            "https://i.gyazo.com/ec67e6abd7e7a5db41d0f6c398748c52.png",
            1
        )
        val omen_3 = Habilidad(
            3,
            "Velo Tenebroso",
            "Omen lanza una sombra que al llegar a la zona indicada se convierte en un área que bloqueará la visión. Puede colocarlas a largas distancias y denegar puntos estratégicos. También puede calcular la altitud a la que caerá la bola.",
            "https://i.gyazo.com/dc597a3f0e3d9e5e1122820c4ffcc264.png",
            1
        )
        val omen_4 = Habilidad(
            4,
            "Desde las Sombras",
            "Omen elige cualquier ubicación del mapa y se teletransporta a ella alterando el mapa de los enemigos durante unos pocos segundos. Tras teletransportarse, tendrá que canalizar su aparición durante esos mismos segundos que altera el mapa enemigo. Si durante ese tiempo Omen recibe daño, la canalización se cancela y Omen no se teletransportará. Esta habilidad produce un leve sonido donde aparezca.",
            "https://i.gyazo.com/8bd7be4dfb71f0b4894ec7a31494fe4f.png",
            1
        )

        val habilidades = listOf(omen_1, omen_2, omen_3, omen_4)

        /*
        for (habilidad in habilidades) {
            Log.d("Habilidad: ", habilidad.nombre)
        }
        */

        Thread(Runnable {
            val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
            try {
                bd.personajeDao().insertAll(omen)
                runOnUiThread {
                    Toast.makeText(this, "¡Personaje Omen introducido!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Error con: Omen ya existe", Toast.LENGTH_SHORT).show()
                }
            }
            bd.close()
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            var contador = 0
            var alguna_insercion = false
            for (habilidad in habilidades) {
                contador++
                try {
                    bd_habilidades.habilidadDao().insertAll(habilidad)
                    alguna_insercion = true
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Error con: Omen - Habilidad $contador", Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                }
            }
            if (alguna_insercion) {
                runOnUiThread {
                    Toast.makeText(this, "¡Habilidades de Omen introducidas!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            bd_habilidades.close()
        }).start()
    }

    @SuppressLint("SetTextI18n")
    fun mostrar_todas_habilidades() {
        test_tv_tituloDatosPersonaje.text = "Lista de habilidades"
        test_tv_datosPersonaje.text = ""
        Thread(Runnable {
            val habilidades: List<Habilidad>
            val bd_habilidades =
                Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
            habilidades = try {
                bd_habilidades.habilidadDao().getAll()
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                listOf<Habilidad>()
            }
            bd_habilidades.close()

            val datos = StringBuilder()

            try {
                if (habilidades.isNotEmpty()) {
                    val bd_personajes = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
                    var contador = 1
                    datos.append("\n")
                    for (habilidad in habilidades) {
                        val personaje_id = habilidad.personaje_id
                        val personaje: Personaje? = try { bd_personajes.personajeDao().findById(personaje_id) } catch (e: Exception) { Personaje(-1, "NoExiste", "SinLink") }
                        if (contador == 1 && personaje?.id is Int && personaje.id != -1) {
                            datos.append("${personaje.nombre.toUpperCase(Locale.ROOT)}\n\n")
                        }

                        datos.append("Habilidad $contador: ${habilidad.nombre}\n")
                        datos.append("ID habilidad: ${habilidad.id} | ID personaje: ${habilidad.personaje_id}\n")
                        //datos.append("Nombre: ${habilidad.nombre}\n")
                        datos.append("Descripción: ${habilidad.descripcion.substring(0, 40)}...\n")
                        datos.append("Link: ${habilidad.link_imagen.substring(0, 40)}...\n")
                        //datos.append("ID Personaje: ${habilidad.personaje_id}\n")

                        if (contador == 4 && habilidad != habilidades.last()) {
                            datos.append("--------------------------------------------------------------------------------\n")
                            datos.append("--------------------------------------------------------------------------------")
                            contador = 0
                        }
                        datos.append("\n")
                        contador++
                    }
                    bd_personajes.close()
                } else {
                    datos.append("No existen habilidades registradas.\n\n")
                }
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                datos.append("Error. No existen habilidades registradas.\n\n")
            }
            runOnUiThread {
                test_tv_datosPersonaje.text = datos
            }
        }).start()

    }

    @SuppressLint("SetTextI18n")
    fun buscar_personaje() {
        test_tv_tituloDatosPersonaje.text = "Datos personaje"
        test_tv_datosPersonaje.text = ""
        val id_seleccionado = try {
            test_et_id.text.toString().toInt()
        } catch (e: Exception) {
            -1
        }
        if (id_seleccionado >= 0) {
            var personaje: Personaje?
            Thread(Runnable {
                val bd_personajes =
                    Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()
                personaje = try {
                    bd_personajes.personajeDao().findById(id_seleccionado)
                } catch (e: Exception) {
                    Personaje(-1, "NoExiste", "SinLink")
                }
                bd_personajes.close()

                if (personaje?.id is Int && personaje?.id != -1) {
                    val habilidades: List<Habilidad>
                    val bd_habilidades =
                        Room.databaseBuilder(this, AppDatabase::class.java, "habilidades").build()
                    habilidades = try {
                        bd_habilidades.habilidadDao().getAllChampAbilities(id_seleccionado)
                    } catch (e: Exception) {
                        listOf<Habilidad>()
                    }
                    bd_habilidades.close()

                    val datos = StringBuilder()
                    datos.append("ID: ${personaje?.id}\n")
                    datos.append("Nombre: ${personaje?.nombre}\n")
                    datos.append("Link Imagen: ${personaje?.link_imagen}\n\n")

                    try {
                        if (habilidades.isNotEmpty()) {
                            var contador = 1
                            for (habilidad in habilidades) {
                                datos.append("Habilidad $contador: ${habilidad.nombre}\n")
                                datos.append("ID habilidad: ${habilidad.id} | ID personaje: ${habilidad.personaje_id}\n")
                                //datos.append("Nombre: ${habilidad.nombre}\n")
                                datos.append("Descripción: ${habilidad.descripcion.substring(0, 40)}...\n")
                                datos.append("Link: ${habilidad.link_imagen}\n")
                                //datos.append("ID Personaje: ${habilidad.personaje_id}\n")
                                datos.append("\n")
                                contador++
                            }
                        } else {
                            datos.append("El personaje no tiene habilidades registradas\n\n")
                        }
                    } catch (e: Exception) {
                        datos.append("Error. El personaje no tiene habilidades registradas\n\n")
                    }
                    runOnUiThread {
                        test_tv_datosPersonaje.text = datos
                    }

                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "No existe un personaje con el id $id_seleccionado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }).start()

        } else {
            Toast.makeText(this, "Error. Introduce un número entero positivo.", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
