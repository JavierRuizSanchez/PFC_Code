package com.utad.tfg

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.utad.tfg.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class admin_Carta1 : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin__carta1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener la referencia de la imagen
        val imgPane = view.findViewById<ImageView>(R.id.producto7_pane7)
        val textNombre = view.findViewById<TextView>(R.id.pane_nombre7)
        val textDetalles = view.findViewById<TextView>(R.id.pane_detalles7)

        // Cambiar la imagen a escala de grises
        val grayscaleFilter = ColorMatrix()
        grayscaleFilter.setSaturation(0f)
        val grayscaleColorFilter = ColorMatrixColorFilter(grayscaleFilter)
        imgPane.colorFilter = grayscaleColorFilter

        // Cambiar la visibilidad de la imagen según sea necesario
        imgPane.visibility = View.GONE

        textNombre.visibility = View.GONE
        textDetalles.visibility = View.GONE// Puedes cambiar esto según tus requisitos
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            admin_Carta1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
