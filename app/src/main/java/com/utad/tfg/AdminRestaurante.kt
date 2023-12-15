package com.utad.tfg


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


class AdminRestaurante : Fragment() {

    private var imageVisibilityListener: OnImageVisibilityListener? = null

    interface OnImageVisibilityListener {
        fun onImageVisibilityChanged(imageResource: Int, isVisible: Boolean)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_restaurante, container, false)

        // Obtener referencias a los botones eliminar y añadir
        val btnEliminarqueso = view.findViewById<Button>(R.id.eliminarcuatroquesos)
        val btnEliminarcarbonara = view.findViewById<Button>(R.id.eliminarcarbonara)
        val btnEliminarprosciutto = view.findViewById<Button>(R.id.eliminarprosciutto)
        val btnEliminarestaciones = view.findViewById<Button>(R.id.eliminarcuatroestaciones)
        val btnEliminarboloñesa = view.findViewById<Button>(R.id.eliminarboloñesa)
        val btnEliminarpesto = view.findViewById<Button>(R.id.eliminarpesto)
        val btnEliminarpane = view.findViewById<Button>(R.id.eliminarpanne)
        val btnEliminarragu = view.findViewById<Button>(R.id.eliminarragu)
        val btnEliminartiramisu = view.findViewById<Button>(R.id.eliminartiramisu)
        val btnEliminarcoppa = view.findViewById<Button>(R.id.eliminarcoppa)

        val btnAñadirqueso = view.findViewById<Button>(R.id.añadircuatroquesos)
        val btnAñadircarbonara = view.findViewById<Button>(R.id.añadircarbonara)
        val btnAñadirprosciutto = view.findViewById<Button>(R.id.añadirprosciutto)
        val btnAñadirestaciones = view.findViewById<Button>(R.id.añadircuatroestaciones)
        val btnAñadirboloñesa = view.findViewById<Button>(R.id.añadirboloñesa)
        val btnAñadirpesto = view.findViewById<Button>(R.id.añadirpesto)
        val btnAñadirpane = view.findViewById<Button>(R.id.añadirpanne)
        val btnAñadirragu = view.findViewById<Button>(R.id.añadirragu)
        val btnAñadirtiramisu = view.findViewById<Button>(R.id.añadirtiramisu)
        val btnAñadircoppa = view.findViewById<Button>(R.id.añadircoppagusto)


        val btnircarta = view.findViewById<Button>(R.id.verCarta)

        btnircarta.setOnClickListener {
            findNavController().navigate(R.id.action_adminRestaurante_to_admin_Carta1)
        }




        // Configurar listeners para los botones
        btnEliminarqueso.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod1, false)
        }

        btnEliminarcarbonara.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod2, false)
        }
        btnEliminarprosciutto.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod3, false)
        }
        btnEliminarboloñesa.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod4, false)
        }
        btnEliminarpesto.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod5, false)
        }
        btnEliminarpane.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod6, false)
            Toast.makeText(requireContext(), "Producto eliminado con éxito", Toast.LENGTH_SHORT).show()
        }
        btnEliminarragu.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod7, false)
        }
        btnEliminartiramisu.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod8, false)
        }
        btnEliminarcoppa.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod9, false)
        }
        btnEliminarestaciones.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod10, false)
        }


        btnAñadirqueso.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod1, true)
        }
        btnAñadircarbonara.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod2, true)
        }
        btnAñadirprosciutto.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod3, true)
        }
        btnAñadirestaciones.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod4, true)
        }
        btnAñadirboloñesa.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod5, true)
        }
        btnAñadirpesto.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod6, true)
        }
        btnAñadirpane.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod7, true)
        }
        btnAñadirragu.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod8, true)
        }
        btnAñadirtiramisu.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod9, true)
        }
        btnAñadircoppa.setOnClickListener {
            imageVisibilityListener?.onImageVisibilityChanged(R.id.tienda_prod10, true)
        }

        return view
    }

    fun setOnImageVisibilityListener(listener: OnImageVisibilityListener) {
        this.imageVisibilityListener = listener
    }

    companion object {
        @JvmStatic
        fun newInstance() = AdminRestaurante()
    }
}




