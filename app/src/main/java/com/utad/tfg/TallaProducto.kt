package com.utad.tfg

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TallaProducto : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_talla_producto, container, false)

        val productImageView = view.findViewById<ImageView>(R.id.productImage)
        productImageView.setImageResource(DataHolder.selectedImageResource)

        val btnInicio:Button = view.findViewById(R.id.btnMenuTallaTienda)
        val tallaSButton = view.findViewById<ToggleButton>(R.id.tallaS)
        val tallaMButton = view.findViewById<ToggleButton>(R.id.tallaM)
        val tallaLButton = view.findViewById<ToggleButton>(R.id.tallaL)
        val tallaXLButton = view.findViewById<ToggleButton>(R.id.tallaXL)
        val añadirCesta = view.findViewById<Button>(R.id.AñadirCesta)

        btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_tallaProducto_to_establecimientos)
        }

        tallaSButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addTallaToPedido("S")
            }
        }

        tallaMButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addTallaToPedido("M")
            }
        }

        tallaLButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addTallaToPedido("L")
            }
        }

        tallaXLButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addTallaToPedido("XL")
            }
        }

        añadirCesta.setOnClickListener {
            Toast.makeText(requireContext(), "Pedido añadido con éxito", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun addTallaToPedido(selectedTalla: String) {

        val apiInterface = ApiClient.apiService

        val idPedido = 35 // Reemplaza con el ID del pedido que deseas actualizar

        // Aquí asumo que tienes un método en la API para obtener un pedido por ID
        apiInterface.getPedidoById(idPedido).enqueue(object : Callback<PedidoModel> {
            override fun onResponse(call: Call<PedidoModel>, response: Response<PedidoModel>) {
                if (response.isSuccessful) {
                    val pedido = response.body()

                    if (pedido != null) {
                        pedido.talla = selectedTalla

                        // Asumiendo que tienes un método en la API para actualizar un pedido
                        apiInterface.updatePedido(pedido.idPedido, pedido).enqueue(object : Callback<ApiResponse> {
                            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Talla añadida al pedido existente con éxito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    // Maneja los errores en caso de que falle la actualización del pedido
                                }
                            }

                            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                // Maneja los errores en caso de fallo en la solicitud
                            }
                        })
                    }
                } else {
                    // Maneja los errores en caso de fallo al obtener el pedido
                }
            }

            override fun onFailure(call: Call<PedidoModel>, t: Throwable) {
                // Maneja los errores en caso de fallo en la solicitud
            }
        })
    }
}
