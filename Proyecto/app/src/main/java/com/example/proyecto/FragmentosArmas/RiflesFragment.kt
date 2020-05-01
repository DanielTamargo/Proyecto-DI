package com.example.proyecto.FragmentosArmas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.proyecto.BBDD.Arma
import com.example.proyecto.OnFragmentActionsListener

import com.example.proyecto.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pistolas.*
import kotlinx.android.synthetic.main.fragment_rifles.*

/**
 * A simple [Fragment] subclass.
 */
class RiflesFragment : Fragment() {

    private var listener: OnFragmentActionsListener? = null
    private val listaArmas: MutableList<Arma> = mutableListOf()
    private var contador = 1

    companion object {
        private val ARG_TITLE="ARG_TITLE"
        private val ARG_COLOR="ARG_COLOR"

        fun newInstance(title: String, color: Int): RiflesFragment {
            val fragment: RiflesFragment = RiflesFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putInt(ARG_COLOR, color)
            }
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            //ponemos titulo y fondo con los arguments si queremos
        } else {
            //si los argumentos no se pasan bien podemos poner errores
        }

        cargar_datos(listaArmas)
        cargar_celdas()
    }

    @SuppressLint("SetTextI18n")
    fun cargar_celdas() {
        //cogemos la referencia para luego colgarle a ese LinearLayout las celdas que construyamos
        val ll_principal = rifles_layout_a_construir

        //params width, height y weight del layout donde estarán el nombre del arma y la foto
        val ll_photo_titulo_params = LinearLayout.LayoutParams(
            0,
            250,
            1.5f
        )

        //params del nombre del arma
        val tv_nombre_arma = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0,
            1.5f
        )

        //params de la foto
        val photo_layout_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0,
            3.5f
        )

        //params width, height y weight del linear layout que va a la par con la foto
        val ll_bloque_tvs_params = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            2.5f
        )

        //params de las filas dentro del contenedor
        val ll_filas_bloque_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        //params de los text views dentro de las filas
        val tv_fila_params = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1f
        )

        //params del view utilizado para dejar un espacio
        val view_salto = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            20
        )

        //params del view utilizado para dejar un gran espacio
        val view_gran_salto = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            200
        )

        //params del view utilizado para trazar una línea
        val linea_params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            5
        )

        //códigos colores
        //softOrange: #b55e12, darkblue: #161b21, softblue: #8AAAE5, notthatsoftblue: #5b81c7
        //códigos colores daños
        //cabeza: #d64036, cuerpo: #db7230, piernas: #d4a633
        var colorcabeza = ""
        var colorcuerpo = ""
        var colorpierna = ""
        //cómo usar los códigos:
        //ejemplo.setBackgroundColor(Color.parseColor("#161b21"))

        //como poner estilo (bold, italic...): https://stackoverflow.com/questions/6200533/how-to-set-textview-textstyle-such-as-bold-italic

        var color: String
        for (arma in listaArmas) {
            // Alternamos el color del fondo para que visualmente sea más agradable
            if (contador % 2 == 0) {
                color = "#5b81c7" //fondo oscuro, colores más oscuros
                colorcabeza = "#611510"
                colorcuerpo = "#822f09"
                colorpierna = "#614601"
            } else {
                color = "#8AAAE5" //fondo claro, colores oscuros normales
                colorcabeza = "#8f241d"
                colorcuerpo = "#ad4415"
                colorpierna = "#7d5b05"
            }
            contador++

            // Linear Layout de la celda, empezamos a construir la celda
            val ll_celda = LinearLayout(context)
            ll_celda.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            ll_celda.orientation = LinearLayout.HORIZONTAL
            ll_celda.setPadding(10, 10, 10, 10)
            ll_celda.setBackgroundColor(Color.parseColor(color))
            //-----------------------------------------------------------------
            // Linear Layout que contendrá el nombre del arma y la foto
            val ll_photo_titulo = LinearLayout(context)
            ll_photo_titulo.layoutParams = ll_photo_titulo_params
            ll_photo_titulo.orientation = LinearLayout.VERTICAL

            // Nombre arma
            val tv_titulo = TextView(context)
            tv_titulo.layoutParams = tv_nombre_arma
            tv_titulo.text = "    ${arma.nombre}"
            tv_titulo.setTextColor(Color.parseColor("#161b21"))
            tv_titulo.setTypeface(null, Typeface.BOLD)

            ll_photo_titulo.addView(tv_titulo)

            // Foto arma
            val photo = ImageView(context)
            Picasso.get().load(arma.link_imagen).placeholder(R.drawable.w_qm_1_qm)
                .into(photo)
            photo.layoutParams = photo_layout_params
            photo.setPadding(0, 10, 15, 0)
            photo.scaleType = ImageView.ScaleType.FIT_CENTER

            ll_photo_titulo.addView(photo)

            // Añadimos la foto y el título a la celda
            ll_celda.addView(ll_photo_titulo)
            //-----------------------------------------------------------------
            // Linear Layout que contendrá el "bloque" de filas de textos
            val ll_bloque = LinearLayout(context)
            ll_bloque.layoutParams = ll_bloque_tvs_params
            ll_bloque.orientation = LinearLayout.VERTICAL
            //-----------------------------------------------------------------
            // Fila 1: Header tabla
            val ll_fila1 = LinearLayout(context)
            ll_fila1.layoutParams = ll_filas_bloque_params
            ll_fila1.orientation = LinearLayout.HORIZONTAL

            val tv_dmg = TextView(context)
            tv_dmg.layoutParams = tv_fila_params
            tv_dmg.gravity = Gravity.CENTER
            tv_dmg.text = "Daño"
            tv_dmg.setTextColor(Color.parseColor("#161b21"))
            ll_fila1.addView(tv_dmg)

            val tv_cabeza = TextView(context)
            tv_cabeza.layoutParams = tv_fila_params
            tv_cabeza.gravity = Gravity.CENTER
            tv_cabeza.text = "Cabeza"
            tv_cabeza.setTextColor(Color.parseColor("#161b21"))
            ll_fila1.addView(tv_cabeza)

            val tv_cuerpo = TextView(context)
            tv_cuerpo.layoutParams = tv_fila_params
            tv_cuerpo.gravity = Gravity.CENTER
            tv_cuerpo.text = "Cuerpo"
            tv_cuerpo.setTextColor(Color.parseColor("#161b21"))
            ll_fila1.addView(tv_cuerpo)

            val tv_piernas = TextView(context)
            tv_piernas.layoutParams = tv_fila_params
            tv_piernas.gravity = Gravity.CENTER
            tv_piernas.text = "Piernas"
            tv_piernas.setTextColor(Color.parseColor("#161b21"))
            ll_fila1.addView(tv_piernas)

            ll_bloque.addView(ll_fila1)
            //-----------------------------------------------------------------
            // Fila 2: Daño cercano
            val ll_fila2 = LinearLayout(context)
            ll_fila2.layoutParams = ll_filas_bloque_params
            ll_fila2.orientation = LinearLayout.HORIZONTAL

            val tv_close = TextView(context)
            tv_close.layoutParams = tv_fila_params
            tv_close.gravity = Gravity.START
            tv_close.text = "0-20m"
            tv_close.setTextColor(Color.parseColor("#161b21"))
            ll_fila2.addView(tv_close)

            val tv_close_cabeza = TextView(context)
            tv_close_cabeza.layoutParams = tv_fila_params
            tv_close_cabeza.gravity = Gravity.CENTER
            tv_close_cabeza.text = arma.closeDamage[0]
            tv_close_cabeza.setTextColor(Color.parseColor(colorcabeza))
            tv_close_cabeza.setTypeface(null, Typeface.BOLD)
            ll_fila2.addView(tv_close_cabeza)

            val tv_close_cuerpo = TextView(context)
            tv_close_cuerpo.layoutParams = tv_fila_params
            tv_close_cuerpo.gravity = Gravity.CENTER
            tv_close_cuerpo.text = arma.closeDamage[1]
            tv_close_cuerpo.setTextColor(Color.parseColor(colorcuerpo))
            tv_close_cuerpo.setTypeface(null, Typeface.BOLD)
            ll_fila2.addView(tv_close_cuerpo)

            val tv_close_pierna = TextView(context)
            tv_close_pierna.layoutParams = tv_fila_params
            tv_close_pierna.gravity = Gravity.CENTER
            tv_close_pierna.text = arma.closeDamage[2]
            tv_close_pierna.setTextColor(Color.parseColor(colorpierna))
            tv_close_pierna.setTypeface(null, Typeface.BOLD)
            ll_fila2.addView(tv_close_pierna)

            ll_bloque.addView(ll_fila2)
            //-----------------------------------------------------------------
            // Fila 3: Daño media distancia
            val ll_fila3 = LinearLayout(context)
            ll_fila3.layoutParams = ll_filas_bloque_params
            ll_fila3.orientation = LinearLayout.HORIZONTAL

            val tv_medium = TextView(context)
            tv_medium.layoutParams = tv_fila_params
            tv_medium.gravity = Gravity.START
            tv_medium.text = "20-30m"
            tv_medium.setTextColor(Color.parseColor("#161b21"))
            ll_fila3.addView(tv_medium)

            val tv_medium_cabeza = TextView(context)
            tv_medium_cabeza.layoutParams = tv_fila_params
            tv_medium_cabeza.gravity = Gravity.CENTER
            tv_medium_cabeza.text = arma.mediumDamage[0]
            tv_medium_cabeza.setTextColor(Color.parseColor(colorcabeza))
            tv_medium_cabeza.setTypeface(null, Typeface.BOLD)
            ll_fila3.addView(tv_medium_cabeza)

            val tv_medium_cuerpo = TextView(context)
            tv_medium_cuerpo.layoutParams = tv_fila_params
            tv_medium_cuerpo.gravity = Gravity.CENTER
            tv_medium_cuerpo.text = arma.mediumDamage[1]
            tv_medium_cuerpo.setTextColor(Color.parseColor(colorcuerpo))
            tv_medium_cuerpo.setTypeface(null, Typeface.BOLD)
            ll_fila3.addView(tv_medium_cuerpo)

            val tv_medium_pierna = TextView(context)
            tv_medium_pierna.layoutParams = tv_fila_params
            tv_medium_pierna.gravity = Gravity.CENTER
            tv_medium_pierna.text = arma.mediumDamage[2]
            tv_medium_pierna.setTextColor(Color.parseColor(colorpierna))
            tv_medium_pierna.setTypeface(null, Typeface.BOLD)
            ll_fila3.addView(tv_medium_pierna)

            ll_bloque.addView(ll_fila3)
            //-----------------------------------------------------------------
            // Fila 4: Daño lejano
            val ll_fila4 = LinearLayout(context)
            ll_fila4.layoutParams = ll_filas_bloque_params
            ll_fila4.orientation = LinearLayout.HORIZONTAL

            val tv_far = TextView(context)
            tv_far.layoutParams = tv_fila_params
            tv_far.gravity = Gravity.START
            tv_far.text = "30-50m"
            tv_far.setTextColor(Color.parseColor("#161b21"))
            ll_fila4.addView(tv_far)

            val tv_far_cabeza = TextView(context)
            tv_far_cabeza.layoutParams = tv_fila_params
            tv_far_cabeza.gravity = Gravity.CENTER
            tv_far_cabeza.text = arma.farDamage[0]
            tv_far_cabeza.setTextColor(Color.parseColor(colorcabeza))
            tv_far_cabeza.setTypeface(null, Typeface.BOLD)
            ll_fila4.addView(tv_far_cabeza)

            val tv_far_cuerpo = TextView(context)
            tv_far_cuerpo.layoutParams = tv_fila_params
            tv_far_cuerpo.gravity = Gravity.CENTER
            tv_far_cuerpo.text = arma.farDamage[1]
            tv_far_cuerpo.setTextColor(Color.parseColor(colorcuerpo))
            tv_far_cuerpo.setTypeface(null, Typeface.BOLD)
            ll_fila4.addView(tv_far_cuerpo)

            val tv_far_pierna = TextView(context)
            tv_far_pierna.layoutParams = tv_fila_params
            tv_far_pierna.gravity = Gravity.CENTER
            tv_far_pierna.text = arma.farDamage[2]
            tv_far_pierna.setTextColor(Color.parseColor(colorpierna))
            tv_far_pierna.setTypeface(null, Typeface.BOLD)
            ll_fila4.addView(tv_far_pierna)

            ll_bloque.addView(ll_fila4)

            val salto = View(context)
            salto.layoutParams = view_salto

            ll_bloque.addView(salto)
            //-----------------------------------------------------------------
            // Fila 5: Tipo arma
            val ll_fila5 = LinearLayout(context)
            ll_fila5.layoutParams = ll_filas_bloque_params
            ll_fila5.orientation = LinearLayout.HORIZONTAL

            val tv_tipo = TextView(context)
            tv_tipo.layoutParams = tv_fila_params
            tv_tipo.gravity = Gravity.END
            tv_tipo.text = "Tipo: "
            tv_tipo.setTextColor(Color.parseColor("#161b21"))
            ll_fila5.addView(tv_tipo)

            val tv_tipo_arma = TextView(context)
            tv_tipo_arma.layoutParams = tv_fila_params
            tv_tipo_arma.gravity = Gravity.START
            tv_tipo_arma.text = " ${arma.tipo}"
            tv_tipo_arma.setTextColor(Color.parseColor("#161b21"))
            tv_tipo_arma.setTypeface(null, Typeface.BOLD)
            ll_fila5.addView(tv_tipo_arma)

            ll_bloque.addView(ll_fila5)
            //-----------------------------------------------------------------
            // Fila 6: Coste arma
            val ll_fila6 = LinearLayout(context)
            ll_fila6.layoutParams = ll_filas_bloque_params
            ll_fila6.orientation = LinearLayout.HORIZONTAL

            val tv_coste = TextView(context)
            tv_coste.layoutParams = tv_fila_params
            tv_coste.gravity = Gravity.END
            tv_coste.text = "Coste: "
            tv_coste.setTextColor(Color.parseColor("#161b21"))
            ll_fila6.addView(tv_coste)

            val tv_coste_arma = TextView(context)
            tv_coste_arma.layoutParams = tv_fila_params
            tv_coste_arma.gravity = Gravity.START
            tv_coste_arma.text = " ${arma.coste}"
            tv_coste_arma.setTextColor(Color.parseColor("#161b21"))
            tv_coste_arma.setTypeface(null, Typeface.BOLD)
            ll_fila6.addView(tv_coste_arma)

            ll_bloque.addView(ll_fila6)
            //-----------------------------------------------------------------
            // Añadimos el bloque ya hecho a la celda
            ll_celda.addView(ll_bloque)

            // Añadimos la celda ya finalizada al Linear Layout principal
            ll_principal.addView(ll_celda)

            // Añadimos una linea de separación para que quede más claro visualmente :3
            val linea = View(context)
            linea.layoutParams = linea_params
            linea.setBackgroundColor(Color.parseColor("#161b21"))
            ll_principal.addView(linea)

        }

        // Un salto para que no se entrecorte la última
        val salto1 = View(context)
        salto1.layoutParams = view_gran_salto
        ll_principal.addView(salto1)
    }

    fun cargar_datos(listaArmas: MutableList<Arma>) {
        val rifle1_bulldog = Arma(12, "Bulldog",
            "https://i.gyazo.com/3b445b6eb74b60b38171f745bcce42a7.png",
            "Rifle Asalto", "2100",
            listOf("116", "35", "30"), listOf("116", "35", "30"), listOf("116", "35", "30"))

        val rifle2_guardian = Arma(13, "Guardian",
            "https://i.gyazo.com/19cd6ab24b1f6406935cf08090c92eab.png",
            "Rifle Asalto (carabina)", "2700",
            listOf("195", "65", "49"), listOf("195", "65", "49"), listOf("195", "65", "49"))

        val rifle3_phantom = Arma(14, "Phantom",
            "https://i.gyazo.com/4534444bb23a8a8c6541ad05e01a8f16.png",
            "Rifle Asalto", "2900",
            listOf("156", "39", "33"), listOf("140", "35", "30"), listOf("124", "31", "26"))

        val rifle4_vandal = Arma(15, "Vandal",
            "https://i.gyazo.com/14d24c5da7b651c8a3302ef9170dd1b4.png",
            "Rifle Asalto", "2900",
            listOf("156", "39", "33"), listOf("156", "39", "33"), listOf("156", "39", "33"))

        val rifle5_marshal = Arma(16, "Marshal",
            "https://i.gyazo.com/5d010c940eb08382fe12ec9bbd5cd192.png",
            "Rifle Francotirador", "1100",
            listOf("202", "101", "85"), listOf("202", "101", "85"), listOf("202", "101", "85"))

        val rifle6_operator = Arma(17, "Operator",
            "https://i.gyazo.com/7d4c4f697aabf8cc67daa43166ece070.png",
            "Rifle Francotirador", "4500",
            listOf("255", "150", "127"), listOf("255", "150", "127"), listOf("255", "150", "127"))

        listaArmas.add(rifle1_bulldog)
        listaArmas.add(rifle2_guardian)
        listaArmas.add(rifle3_phantom)
        listaArmas.add(rifle4_vandal)
        listaArmas.add(rifle5_marshal)
        listaArmas.add(rifle6_operator)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rifles, container, false)
    }

}
