package com.utad.tfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mis_reservas.newInstance] factory method to
 * create an instance of this fragment.
 */
class mis_reservas : Fragment() {

    private lateinit var recyclerView: RecyclerView;

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
        val view = inflater.inflate(R.layout.fragment_mis_reservas, container, false)
        recyclerView = view.findViewById(R.id.RecyclerViewListReserva)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MisReservasAdapter(getMisreservas())
        recyclerView.adapter = adapter


        // val btnMenu = view.findViewById<Button>(R.id.btnMenu)
        // btnMenu.setOnClickListener {
        //  findNavController().navigate(R.id.action_establecimientos_to_menu_app)
        //}
        return view

    }

    private fun getMisreservas(): List<Misreservas> {
        // Aquí debes obtener los datos de los establecimientos de tu base de datos
        // y retornarlos como una lista de objetos Establecimiento.
        // Por ejemplo:
        val Reserva1 = Misreservas("Nombre1", "Establecimiento")
        val Reserva2 = Misreservas("Nombre2", "Establecimiento")
        val Reserva3 = Misreservas("Nombre3", "Establecimiento" )
        return listOf(Reserva1, Reserva2,Reserva3)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mis_reservas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mis_reservas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}