package com.example.proyecto.FragmentosMapas

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.proyecto.OnFragmentActionsListener
import com.example.proyecto.R
import kotlinx.android.synthetic.main.fragment_bind.*
import java.lang.Exception

class BindFragment : Fragment() {

    private var listener: OnFragmentActionsListener? = null

    companion object {
        private val ARG_TITLE="ARG_TITLE"
        private val ARG_COLOR="ARG_COLOR"

        fun newInstance(title: String, color: Int): BindFragment {
            val fragment: BindFragment = BindFragment()
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
            bind_titulo.text = try { arguments?.getString(ARG_TITLE) ?: "ERROR" } catch (e: Exception) { "Bind" }
            //view.setBackgroundColor(arguments?.getInt(ARG_COLOR) ?: R.color.white)
        } else {
            bind_titulo.text = "ERROR"
            //view.setBackgroundColor(R.color.white)
        }

        //Subrayar título
        //Forma 1: Spannable, método correcto
        val titulo: TextView = view.findViewById(R.id.bind_titulo) ?: TextView(context)
        val contenido = SpannableString("BIND")
        contenido.setSpan(UnderlineSpan(), 0, contenido.length, 0)
        titulo.setText(contenido)

        //Forma 2: Html.fromHtml <- está deprecated
        //val titulo: TextView = findViewById(R.id.bind_titulo)
        //titulo.setText(Html.fromHtml(String.format(getString(R.string.bind))))

        bind_checkBox_defensores.performClick()
        bind_checkBox_defensores.performClick()
        //bind_checkBox_defensores.isChecked = true
        //bind_checkBox_defensores.isChecked = false

        //Listener checkbox
        bind_checkBox_defensores.setOnClickListener {
            listener?.onCLickFragmentButton("bind", 0)
        }
        bind_tv_volver.setOnClickListener {
            listener?.onCLickFragmentButton("bind", 1)
        }
        bind_egg_1.setOnClickListener {
            listener?.onCLickFragmentButton("bind", 2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bind, container, false)
    }

}
