package com.utad.tfg

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [admin_Tienda1.newInstance] factory method to
 * create an instance of this fragment.
 */
class admin_Tienda1 : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin__tienda1, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener la referencia de la imagen
        val imgPane = view.findViewById<ImageButton>(R.id.tienda_prod22)
        val textNombre = view.findViewById<TextView>(R.id.zapatillas_nombre2)
        val textDetalles = view.findViewById<TextView>(R.id.zapatillas_detalles2)

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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment admin_Tienda1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            admin_Tienda1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}