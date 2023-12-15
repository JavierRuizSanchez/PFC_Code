package com.utad.tfg

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Carta_Restaurante : Fragment() {

    private lateinit var restaurante: RestauranteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carta__restaurante, container, false)

        val txtProducto1 = view.findViewById<TextView>(R.id.cuatroformaggi_nombre)
        val txtProducto2 = view.findViewById<TextView>(R.id.carbonara_nombre)
        val txtProducto3 = view.findViewById<TextView>(R.id.prosciutto_nombre)
        val txtProducto4 = view.findViewById<TextView>(R.id.cuatroestaciones_nombre)
        val txtProducto5 = view.findViewById<TextView>(R.id.bolonesa_nombre)
        val txtProducto6 = view.findViewById<TextView>(R.id.pesto_nombre)
        val txtProducto7 = view.findViewById<TextView>(R.id.pane_nombre)
        val txtProducto8 = view.findViewById<TextView>(R.id.ragu_nombre)
        val txtProducto9 = view.findViewById<TextView>(R.id.tiramisu_nombre)
        val txtProducto10 = view.findViewById<TextView>(R.id.coppagusto_nombre)
        val btnHacerPed = view.findViewById<TextView>(R.id.ir_a_pedido)


        btnHacerPed.setOnClickListener {
            findNavController().navigate(R.id.action_carta_Restaurante_to_hacerPedidoRestaurante)
        }
        // Aquí debes obtener los datos del restaurante desde tu base de datos.
        // Puedes usar tu método existente para obtener los datos del restaurante por su ID.

        // Ejemplo de obtención de datos del restaurante por su ID utilizando Retrofit y la clase ApiClient:
        val apiService = ApiClient.apiService
        val restauranteId = 1 // ID del restaurante para el que deseas mostrar los productos

        val restauranteCall = apiService.getRestauranteById(restauranteId)
        restauranteCall.enqueue(object : Callback<RestauranteModel> {
            override fun onResponse(
                call: Call<RestauranteModel>,
                response: Response<RestauranteModel>
            ) {
                if (response.isSuccessful) {
                    restaurante = response.body()!!
                    mostrarProductosEnBotones()
                } else {
                    // Manejar respuesta de error
                    Log.e("Carta_Restaurante", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RestauranteModel>, t: Throwable) {
                // Manejar error de la llamada
            }
        })


        return view
    }

    private fun mostrarProductosEnBotones() {
        val txtProducto1 = view?.findViewById<TextView>(R.id.cuatroformaggi_nombre)
        val txtProducto2 = view?.findViewById<TextView>(R.id.carbonara_nombre)
        val txtProducto3 = view?.findViewById<TextView>(R.id.prosciutto_nombre)
        val txtProducto4 = view?.findViewById<TextView>(R.id.cuatroestaciones_nombre)
        val txtProducto5 = view?.findViewById<TextView>(R.id.bolonesa_nombre)
        val txtProducto6 = view?.findViewById<TextView>(R.id.pesto_nombre)
        val txtProducto7 = view?.findViewById<TextView>(R.id.pane_nombre)
        val txtProducto8 = view?.findViewById<TextView>(R.id.ragu_nombre)
        val txtProducto9 = view?.findViewById<TextView>(R.id.tiramisu_nombre)
        val txtProducto10 = view?.findViewById<TextView>(R.id.coppagusto_nombre)

        txtProducto1?.text = restaurante.producto1
        txtProducto2?.text = restaurante.producto2
        txtProducto3?.text = restaurante.producto3
        txtProducto4?.text = restaurante.producto4
        txtProducto5?.text = restaurante.producto5
        txtProducto6?.text = restaurante.producto6
        txtProducto7?.text = restaurante.producto7
        txtProducto8?.text = restaurante.producto8
        txtProducto9?.text = restaurante.producto9
        txtProducto10?.text = restaurante.producto10

        // Aquí puedes agregar el código para manejar los clics en los botones de los productos
        val producto1ImageView = view?.findViewById<ImageView>(R.id.producto1_cuatroquesos)
        val producto2ImageView = view?.findViewById<ImageView>(R.id.producto2_carbonara)
        val producto3ImageView = view?.findViewById<ImageView>(R.id.producto3_prosciutto)
        val producto4ImageView = view?.findViewById<ImageView>(R.id.producto4_cuatroestaciones)
        val producto5ImageView = view?.findViewById<ImageView>(R.id.producto5_bolonesa)
        val producto6ImageView = view?.findViewById<ImageView>(R.id.producto6_pesto)
        val producto7ImageView = view?.findViewById<ImageView>(R.id.producto7_pane)
        val producto8ImageView = view?.findViewById<ImageView>(R.id.producto8_ragu)
        val producto9ImageView = view?.findViewById<ImageView>(R.id.producto9_tiramisu)
        val producto10ImageView = view?.findViewById<ImageView>(R.id.producto10_coppagusto)

    }



    companion object {
        @JvmStatic
        fun newInstance() = Carta_Restaurante()
    }
}

