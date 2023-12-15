package com.utad.tfg

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [menu_app.newInstance] factory method to
 * create an instance of this fragment.
 */
class menu_app : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_menu_app, container, false)
        val btn_perfil = view.findViewById<Button>(R.id.btn_irperfil)
        val btn_mispedidos = view.findViewById<Button>(R.id.btn_mispedidos)
        val btn_misreservas = view.findViewById<Button>(R.id.btn_misreservas)
        btn_perfil.setOnClickListener {
            findNavController().navigate(R.id.action_menu_app_to_perfil_usuario)
        }
        btn_mispedidos.setOnClickListener {
            findNavController().navigate(R.id.action_menu_app_to_tiposPedido)
        }
        btn_misreservas.setOnClickListener {
            findNavController().navigate(R.id.action_menu_app_to_tiposReserva)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment menu_app.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            menu_app().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}