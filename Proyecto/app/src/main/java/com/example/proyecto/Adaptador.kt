package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Adaptador: RecyclerView.Adapter<Adaptador.ViewHolder>() {

    var opciones_main: MutableList<String> = opciones()
    lateinit var context: Context

    private fun opciones(): MutableList<String> {
        val opciones = mutableListOf<String>()
        opciones.add("Mapas")
        opciones.add("Personajes")
        opciones.add("Armas")
        return opciones
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v), View.OnClickListener {
        var tv_num: TextView
        var card_view: CardView
        var tv_desc: TextView
        var fondoImagen: ImageView
        init {
            card_view = v.findViewById(R.id.card_view)
            tv_num = v.findViewById(R.id.tv_num)
            tv_desc = v.findViewById(R.id.tv_descripcion)
            fondoImagen = v.findViewById(R.id.fondoImagen)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context

            when (tv_num.text) {
                "Mapas" -> {
                    val intent = Intent(context, VentanaMapaHaven::class.java)
                    context.startActivity(intent)
                    //Toast.makeText(context, "¡¡Mapas!!", Toast.LENGTH_SHORT).show()
                }
                "Personajes" -> {
                    val intent = Intent(context, VentanaPersonajes::class.java)
                    context.startActivity(intent)
                    //Toast.makeText(context, "¡¡Personajes!!", Toast.LENGTH_SHORT).show()
                }
                "Armas" -> {
                    val intent = Intent(context, VentanaArmas::class.java)
                    context.startActivity(intent)
                    //Toast.makeText(context, "¡¡Armas!!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "¡¡Has encontrado el primer Easter Egg!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //Situar celda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_celda, parent, false)
        return ViewHolder(v)
    }

    //Número de celdas a devolver
    override fun getItemCount(): Int {
        //como la última se ve incompleta si tengo la toolbar, intentaré añadir una y a la útlima le quitaré todos los elementos o la haré invisible
        return opciones_main.size + 1
    }

    //Modificar lo que vaya a salir en la celda
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //La última que sale se buggea, así que creamos una de más pero en vez de darle info, la escondemos
        //Creo que esto puede ocasionar algún bug, pero mejor eso a que la última tarjeta no salga, ¿no?
        if (position == opciones_main.size) {
            holder.card_view.visibility = View.INVISIBLE
        } else {
            if (holder.card_view.visibility == View.INVISIBLE) holder.card_view.visibility = View.VISIBLE
            holder.tv_num.text = opciones_main[position]
            if (position == 0) {
                //Mapas
                holder.fondoImagen.setImageResource(R.drawable.valorant_cardview_mapas)
                holder.tv_desc.text = "Aprende cuales son los puntos fuertes y débiles de cada mapa, y conviértete en un jugador capaz de dominarlos."
            } else if (position == 1) {
                //Personajes
                holder.fondoImagen.setImageResource(R.drawable.valorant_cardview_personajes)
                holder.tv_desc.text = "Echa un vistazo a cada personaje y sus habilidades. Entender cómo funcionan los distintos personajes te ayudará a comprender mejor el juego y así mejorar como jugador."
            } else if (position == 2) {
                //Armas
                holder.fondoImagen.setImageResource(R.drawable.valorant_cardview_armas)
                holder.tv_desc.text = "Cada arma tiene su utilidad. Aquí encontrarás un listado con todas las armas y sus especificaciones como tipo, precio o tabla de daños."
            }
        }
    }

}