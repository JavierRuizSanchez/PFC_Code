package com.utad.tfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Establecimientos : Fragment(), EstablecimientoAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_establecimientos, container, false)
        recyclerView = view.findViewById(R.id.RecyclerViewList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = EstablecimientoAdapter(getEstablecimientos())
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        val btnMenu = view.findViewById<Button>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_establecimientos_to_menu_app)
        }
        return view
    }

    override fun onItemClick(establecimiento: Establecimiento) {
        val action = when (establecimiento.nombre) {
            "La Tagliatella" -> R.id.action_establecimientos_to_restaurante
            "El Corte Ingles" -> R.id.action_establecimientos_to_tienda
            "Marco Aldany" -> R.id.action_establecimientos_to_peluqueria
            else -> return // No se define acción para el establecimiento seleccionado
        }
        findNavController().navigate(action)
    }

    private fun getEstablecimientos(): List<Establecimiento> {
        // Aquí debes obtener los datos de los establecimientos de tu base de datos
        // y retornarlos como una lista de objetos Establecimiento.
        // Por ejemplo:
        val establecimiento1 = Establecimiento("La Tagliatella", "Camilo Jose Cela 3, 28232, Las Rozas", "916403132", "tagliatella@gmail.com", "https://quadernillos.com/wp-content/uploads/2020/08/qua-operadores-logo-tagliatella.png")
        val establecimiento2 = Establecimiento("El Corte Ingles", "Ctra. de La Coruña, km 12, 5, 28223 Madrid", "917089200", "elcorteingles@gmail.com", "https://www.liderlogo.es/wp-content/uploads/2022/12/pasted-image-0-10.jpg")
        val establecimiento3 = Establecimiento("Marco Aldany", "Av. de la Constitución, 4, 28231 Las Rozas", "916657453", "marcoaldany@gmail.com", "https://www.marcoaldany.com/wp-content/uploads/2018/11/marcoaldany-logo3.png")
        return listOf(establecimiento1, establecimiento2, establecimiento3)
    }
}
