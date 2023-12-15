package com.utad.tfg

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HacerPedidoRestaurante : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var productosAgregados = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hacer_pedido_restaurante, container, false)

        val time: TimePicker = view.findViewById(R.id.timePicker)
        val productSpinner: Spinner = view.findViewById(R.id.ProductosCarta)
        val hacerPedidoButton: Button = view.findViewById(R.id.HacerPedido)
        val añadirListaButton: Button = view.findViewById(R.id.AñadirLista)
        val nombrePed: EditText = view.findViewById(R.id.NombrePedidoCarta)
        val MisPed: Button = view.findViewById(R.id.MisPedidosIrPed)

        val apiInterface = ApiClient.apiService
        val call = apiInterface.getRestaurantes()



        MisPed.setOnClickListener {
            findNavController().navigate(R.id.action_hacerPedidoRestaurante_to_tiposPedido)
        }

        call.enqueue(object : Callback<List<RestauranteModel>> {
            override fun onResponse(
                call: Call<List<RestauranteModel>>,
                response: Response<List<RestauranteModel>>
            ) {
                if (response.isSuccessful) {
                    val restaurantes = response.body()
                    if (!restaurantes.isNullOrEmpty()) {
                        val productos = listOf(
                            restaurantes[0].producto1,
                            restaurantes[0].producto2,
                            restaurantes[0].producto3,
                            restaurantes[0].producto4,
                            restaurantes[0].producto5,
                            restaurantes[0].producto6,
                            restaurantes[0].producto7,
                            restaurantes[0].producto8,
                            restaurantes[0].producto9,
                            restaurantes[0].producto10
                        )

                        val productAdapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            productos
                        )
                        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        productSpinner.adapter = productAdapter

                        hacerPedidoButton.setOnClickListener {
                            val productoSeleccionado = productSpinner.selectedItem as String
                            val horaPedido = obtenerHoraSeleccionada(time)
                            val nombre = nombrePed.text.toString()


                            Log.d("HacerPedidoRestaurante", "Producto seleccionado: $productoSeleccionado")

                            Log.d("HacerPedidoRestaurante", "Hora seleccionada: $horaPedido")

                            Log.d("HacerPedidoRestaurante", "Nombre seleccionado: $nombre")

                            val pedido = PedidoModel(
                                idPedido = 0,
                                nombreProducto = productoSeleccionado,
                                fecha = "16/06/2023",
                                hora = horaPedido,
                                precio = "",
                                idUsuario = 0,
                                idEstablecimiento = 1,
                                nombre = nombre,
                                talla = ""
                            )

                            val pedidoCall = apiInterface.createPedido(pedido)
                            pedidoCall.enqueue(object : Callback<ApiResponse> {
                                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                                    if (response.isSuccessful) {
                                        activity?.runOnUiThread {
                                            Toast.makeText(
                                                requireContext(),
                                                "Pedido realizado con éxito",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody.isNullOrEmpty()) {
                                            "Error al crear el pedido1"
                                        } else {
                                            try {
                                                val errorResponse = Gson().fromJson(
                                                    errorBody,
                                                    ErrorResponse::class.java
                                                )
                                                errorResponse?.message ?: "Error al crear el pedido2"
                                            } catch (e: Exception) {
                                                "Error al crear el pedido3"
                                            }
                                        }

                                        // Manejar el error según tus necesidades
                                        activity?.runOnUiThread {
                                            Toast.makeText(
                                                requireContext(),
                                                errorMessage,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                    // Error en la llamada
                                    // Manejar el error según tus necesidades
                                    Toast.makeText(
                                        requireContext(),
                                        "Error en la llamada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        }

                        añadirListaButton.setOnClickListener {
                            val productoSeleccionado = productSpinner.selectedItem as String
                            val horaPedido = obtenerHoraSeleccionada(time)
                            val nombre = nombrePed.text.toString()


                            Log.d("HacerPedidoRestaurante", "Producto seleccionado: $productoSeleccionado")

                            Log.d("HacerPedidoRestaurante", "Hora seleccionada: $horaPedido")

                            Log.d("HacerPedidoRestaurante", "Nombre seleccionado: $nombre")

                            val pedido = PedidoModel(
                                idPedido = 0,
                                nombreProducto = productoSeleccionado,
                                fecha = "16/06/2023",
                                hora = horaPedido,
                                precio = "",
                                idUsuario = 0,
                                idEstablecimiento = 1,
                                nombre = nombre,
                                talla = ""
                            )

                            val pedidoCall = apiInterface.createPedido(pedido)
                            pedidoCall.enqueue(object : Callback<ApiResponse> {
                                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                                    if (response.isSuccessful) {
                                        productosAgregados++
                                        mostrarMensajeProductosAgregados()
                                        activity?.runOnUiThread {
                                            Toast.makeText(
                                                requireContext(),
                                                "Pedido añadido con éxito",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody.isNullOrEmpty()) {
                                            "Error al añadir el pedido1"
                                        } else {
                                            try {
                                                val errorResponse = Gson().fromJson(
                                                    errorBody,
                                                    ErrorResponse::class.java
                                                )
                                                errorResponse?.message ?: "Error al añadir el pedido2"
                                            } catch (e: Exception) {
                                                "Error al añadir el pedido3"
                                            }
                                        }

                                        // Manejar el error según tus necesidades
                                        activity?.runOnUiThread {
                                            Toast.makeText(
                                                requireContext(),
                                                errorMessage,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                    // Error en la llamada
                                    // Manejar el error según tus necesidades
                                    Toast.makeText(
                                        requireContext(),
                                        "Error en la llamada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        }

                    }
                }
            }


            override fun onFailure(call: Call<List<RestauranteModel>>, t: Throwable) {
                // Error en la llamada
                // Manejar el error según tus necesidades
                Toast.makeText(requireContext(), "Error en la llamada", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    private fun mostrarMensajeProductosAgregados() {
        val mensaje = "Productos añadidos: $productosAgregados"
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()

        // Programa la eliminación del mensaje después de 3 segundos

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HacerPedidoRestaurante()
    }

    // Método para obtener la hora seleccionada en el timePicker
    private fun obtenerHoraSeleccionada(timePicker: TimePicker): String {
        val hour = timePicker.currentHour
        val minute = timePicker.currentMinute

        return "$hour:$minute" // Formato HH:mm
    }
}
