package com.utad.tfg

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class Tienda : Fragment() {

    private lateinit var tienda: TiendaModel
    private lateinit var apiService: ApiInterface

    private var imageSelectedListener: OnImageSelectedListener? = null

    interface OnImageSelectedListener {
        fun onImageSelected(imageResource: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verifica que el contexto (generalmente la actividad) implemente la interfaz
        if (context is OnImageSelectedListener) {
            imageSelectedListener = context
        } else {
            throw RuntimeException("$context debe implementar OnImageSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tienda, container, false)

        val btnProducto1 = view.findViewById<ImageButton>(R.id.tienda_prod1)
        val btnProducto2 = view.findViewById<ImageButton>(R.id.tienda_prod2)
        val btnProducto3 = view.findViewById<ImageButton>(R.id.tienda_prod3)
        val btnProducto4 = view.findViewById<ImageButton>(R.id.tienda_prod4)
        val btnProducto5 = view.findViewById<ImageButton>(R.id.tienda_prod5)
        val btnProducto6 = view.findViewById<ImageButton>(R.id.tienda_prod6)
        val btnProducto7 = view.findViewById<ImageButton>(R.id.tienda_prod7)
        val btnProducto8 = view.findViewById<ImageButton>(R.id.tienda_prod8)
        val btnProducto9 = view.findViewById<ImageButton>(R.id.tienda_prod9)
        val btnProducto10 = view.findViewById<ImageButton>(R.id.tienda_prod10)
        val MisPedTienda = view.findViewById<Button>(R.id.MisPedidosTiendaIrPed)


        MisPedTienda.setOnClickListener {
            findNavController().navigate(R.id.action_tienda_to_tiposPedido)
        }

        apiService = ApiClient.apiService

        val tiendaId = 2 // ID de la tienda para la que deseas mostrar los productos

        val tiendaCall = apiService.getTiendaById(tiendaId)
        tiendaCall.enqueue(object : Callback<TiendaModel> {
            override fun onResponse(
                call: Call<TiendaModel>,
                response: Response<TiendaModel>
            ) {
                if (response.isSuccessful) {
                    tienda = response.body()!!
                    mostrarProductosEnBotones()
                } else {
                    // Manejar respuesta de error
                }
            }

            override fun onFailure(call: Call<TiendaModel>, t: Throwable) {
                // Manejar error de la llamada
            }
        })

        btnProducto1.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.conjuntochandal
            imageSelectedListener?.onImageSelected(R.id.tienda_prod1)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto1)
        }

        btnProducto2.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.zapatillas
            imageSelectedListener?.onImageSelected(R.id.tienda_prod2)
            findNavController().navigate(R.id.action_tienda_to_tallaZapatillas)
            realizarPedido(tienda.producto2)
        }

        btnProducto3.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.vestidonegro
            imageSelectedListener?.onImageSelected(R.id.tienda_prod3)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto3)
        }

        btnProducto4.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.polodehombremangalargarayas
            imageSelectedListener?.onImageSelected(R.id.tienda_prod4)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto4)
        }

        btnProducto5.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.poloemilio
            imageSelectedListener?.onImageSelected(R.id.tienda_prod5)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto5)
        }

        btnProducto6.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.polodustin
            imageSelectedListener?.onImageSelected(R.id.tienda_prod6)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto6)
        }
        btnProducto7.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.pulseralacoste
            imageSelectedListener?.onImageSelected(R.id.tienda_prod7)
            realizarPedido(tienda.producto7)
            Toast.makeText(requireContext(), "Producto añadido a la cesta", Toast.LENGTH_SHORT).show()
        }

        btnProducto8.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.gabardina
            imageSelectedListener?.onImageSelected(R.id.tienda_prod8)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto8)
        }

        btnProducto9.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.pendientes
            imageSelectedListener?.onImageSelected(R.id.tienda_prod9)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto9)
        }

        btnProducto10.setOnClickListener {
            DataHolder.selectedImageResource = R.drawable.camisamujer
            imageSelectedListener?.onImageSelected(R.id.tienda_prod10)
            findNavController().navigate(R.id.action_tienda_to_tallaProducto)
            realizarPedido(tienda.producto10)
        }

        return view
    }

    private fun mostrarProductosEnBotones() {
        val btnProducto1 = view?.findViewById<ImageButton>(R.id.tienda_prod1)
        val btnProducto2 = view?.findViewById<ImageButton>(R.id.tienda_prod2)
        val btnProducto3 = view?.findViewById<ImageButton>(R.id.tienda_prod3)
        val btnProducto4 = view?.findViewById<ImageButton>(R.id.tienda_prod4)
        val btnProducto5 = view?.findViewById<ImageButton>(R.id.tienda_prod5)
        val btnProducto6 = view?.findViewById<ImageButton>(R.id.tienda_prod6)
        val btnProducto7 = view?.findViewById<ImageButton>(R.id.tienda_prod7)
        val btnProducto8 = view?.findViewById<ImageButton>(R.id.tienda_prod8)
        val btnProducto9 = view?.findViewById<ImageButton>(R.id.tienda_prod9)
        val btnProducto10 = view?.findViewById<ImageButton>(R.id.tienda_prod10)


        val span1 = SpannableString("Producto 1")
        val span2 = SpannableString("Producto 2")
        val span3 = SpannableString("Producto 3")
        val span4 = SpannableString("Producto 4")
        val span5 = SpannableString("Producto 5")
        val span6 = SpannableString("Producto 6")
        val span7 = SpannableString("Producto 7")
        val span8 = SpannableString("Producto 8")
        val span9 = SpannableString("Producto 9")
        val span10 = SpannableString("Producto 10")

        // Asignar imágenes a cada SpannableString si es necesario
        // Puedes usar ImageSpan para agregar una imagen a un SpannableString

        // Establecer el SpannableString en el ImageButton
        btnProducto1?.setImageDrawable(resources.getDrawable(R.drawable.conjuntochandal))
        btnProducto2?.setImageDrawable(resources.getDrawable(R.drawable.zapatillas))
        btnProducto3?.setImageDrawable(resources.getDrawable(R.drawable.vestidonegro))
        btnProducto4?.setImageDrawable(resources.getDrawable(R.drawable.polodehombremangalargarayas))
        btnProducto5?.setImageDrawable(resources.getDrawable(R.drawable.poloemilio))
        btnProducto6?.setImageDrawable(resources.getDrawable(R.drawable.polodustin))
        btnProducto7?.setImageDrawable(resources.getDrawable(R.drawable.pulseralacoste))
        btnProducto8?.setImageDrawable(resources.getDrawable(R.drawable.gabardina))
        btnProducto9?.setImageDrawable(resources.getDrawable(R.drawable.pendientes))
        btnProducto10?.setImageDrawable(resources.getDrawable(R.drawable.camisamujer))
    }


    private fun realizarPedido(producto: String) {
        // Aquí puedes realizar la inserción del pedido en la base de datos

        val idUsuario = 0 // ID del usuario que realiza el pedido
        val idEstablecimiento = tienda.idTienda
        val fecha = obtenerFechaActual()
        val hora = obtenerHoraActual()
        val nombreProducto = producto
        val precio = obtenerPrecioDelProducto(producto)
        val nombre = obtenerNombreP()

        val pedido = PedidoModel(0, idUsuario, idEstablecimiento, fecha, hora, nombreProducto, precio, nombre, talla = "")

        val pedidoCall = apiService.createPedido(pedido)
        pedidoCall.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Pedido creado exitosamente
                    //Toast.makeText(requireContext(), "Pedido realizado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    // Manejar respuesta de error
                    Toast.makeText(requireContext(), "No se ha podido realizar el pedido", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar error de la llamada
            }
        })
    }

    private fun obtenerFechaActual(): String {
        // Implementa la lógica para obtener la fecha actual en el formato deseado
        return ""
    }

    private fun obtenerHoraActual(): String {
        // Implementa la lógica para obtener la hora actual en el formato deseado
        return ""
    }

    private fun obtenerPrecioDelProducto(producto: String): String {
        // Implementa la lógica para obtener el precio del producto
        return ""
    }

    private fun obtenerNombreP(): String {
        // Implementa la lógica para obtener el precio del producto
        return ""
    }

    companion object {
        @JvmStatic
        fun newInstance() = Tienda()
    }
}
