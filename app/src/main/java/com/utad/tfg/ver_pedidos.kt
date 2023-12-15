package com.utad.tfg

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ver_pedidos : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pedidoAdapter: PedidoAdapter
    private lateinit var btnEliminar: Button
    private lateinit var btnEliminarSeleccionado: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ver_pedidos, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPedidos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        pedidoAdapter = PedidoAdapter()
        recyclerView.adapter = pedidoAdapter
        btnEliminar = view.findViewById(R.id.btn_eliminarPedRest)
        val btnInicio: Button = view.findViewById(R.id.btnMenuPedRes)
        btnEliminarSeleccionado = view.findViewById(R.id.btn_eliminarPedidoSeleccionado)

        btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_ver_pedidos_to_establecimientos)
        }

        obtenerPedidosRestaurante(1) // Cambia el 2 por el idEstablecimiento correcto
        btnEliminar.setOnClickListener {
            mostrarDialogoAviso()// Cambia el 2 por el idEstablecimiento correcto
        }

        btnEliminarSeleccionado.setOnClickListener {
            val pedidoSeleccionado = pedidoAdapter.pedidoSeleccionado
            if (pedidoSeleccionado != null) {
                mostrarDialogoAvisoSel()
            } else {
                Toast.makeText(context, "No has seleccionado ningún producto", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun eliminarPedidoSeleccionado(idPedido: Int) {
        val apiService = ApiClient.apiService
        val deletePedidoCall = apiService.eliminarPedido(idPedido)

        deletePedidoCall.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Eliminación exitosa, puedes realizar alguna acción adicional si es necesario
                    Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show()
                    // Actualiza la lista de pedidos después de la eliminación
                    obtenerPedidosRestaurante(1) // Cambia el 2 por el idEstablecimiento correcto
                } else {
                    // Manejar respuesta de error
                    Toast.makeText(
                        context,
                        "No se pudo eliminar el pedido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar error de la llamada
                Toast.makeText(context, "Error en la solicitud", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mostrarDialogoAviso() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¡Aviso! Esta acción eliminará todos los productos de su pedido.")
            .setPositiveButton("Aceptar") { dialog, _ ->
                // Acciones al hacer clic en Aceptar
                dialog.dismiss()
                eliminarPedidosRestaurante(1) // Cambia el 2 por el idEstablecimiento correcto
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                // Acciones al hacer clic en Cancelar
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun mostrarDialogoAvisoSel() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¡Aviso! Esta acción eliminará el producto seleccionado.")
            .setPositiveButton("Aceptar") { dialog, _ ->
                // Acciones al hacer clic en Aceptar
                dialog.dismiss()
                eliminarPedidoSeleccionado(75) // Cambia el 2 por el idEstablecimiento correcto
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                // Acciones al hacer clic en Cancelar
                dialog.dismiss()
            }
            .create()
            .show()
    }
    private fun eliminarPedidosRestaurante(idEstablecimiento: Int) {
        val apiService = ApiClient.apiService
        val deletePedidosCall = apiService.deletePedidoByIdEstablecimiento(idEstablecimiento)

        deletePedidosCall.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Eliminación exitosa, puedes realizar alguna acción adicional si es necesario
                    Toast.makeText(context, "Pedidos eliminados", Toast.LENGTH_SHORT).show()
                    // Actualiza la lista de pedidos después de la eliminación
                    obtenerPedidosRestaurante(idEstablecimiento)
                } else {
                    // Manejar respuesta de error
                    Toast.makeText(context, "No se pudieron eliminar los pedidos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar error de la llamada
                Toast.makeText(context, "Error en la solicitud", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun obtenerPedidosRestaurante(idEstablecimiento: Int) {
        val apiInterface = ApiClient.apiService
        val pedidosCall = apiInterface.getPedidosByIdEstablecimiento(idEstablecimiento)

        pedidosCall.enqueue(object : Callback<List<PedidoModel>> {
            override fun onResponse(call: Call<List<PedidoModel>>, response: Response<List<PedidoModel>>) {
                if (response.isSuccessful) {
                    val listaPedidos = response.body()
                    if (listaPedidos != null && listaPedidos.isNotEmpty()) {
                        pedidoAdapter.setPedidos(listaPedidos)
                    } else {
                        Toast.makeText(context, "No se encontraron pedidos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Error al obtener los pedidos.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PedidoModel>>, t: Throwable) {
                Toast.makeText(context, "Error en la llamada.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ver_pedidos_tienda().apply {
                arguments = Bundle().apply {
                    putString(param1, param1)
                    putString(param2, param2)
                }
            }
    }
}
