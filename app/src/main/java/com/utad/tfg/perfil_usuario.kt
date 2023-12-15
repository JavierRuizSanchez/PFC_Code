package com.utad.tfg

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [perfil_usuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class perfil_usuario : Fragment() {
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil_usuario, container, false)
        val txtCorreo = view.findViewById<TextView>(R.id.txt_correo_usuario)
        val txtNombre = view.findViewById<TextView>(R.id.txt_nombre_usuario)

        // Aquí debes implementar la lógica para obtener los datos del usuario desde tu base de datos.
        // Puedes usar tu método existente para obtener los datos del usuario actualmente conectado.

        // Ejemplo de obtención de datos del usuario por su ID utilizando Retrofit y la clase ApiClient:
        val apiService = ApiClient.apiService
        val userId = 8 // ID del usuario actualmente conectado (debes obtenerlo de tu lógica de autenticación)

        val usuarioCall = apiService.getUsuarioById(userId)
        usuarioCall.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                if (response.isSuccessful) {
                    val usuario = response.body()

                    // Asigna los valores del usuario a los campos correspondientes
                    txtCorreo.text = usuario?.email
                    txtNombre.text = usuario?.nombre
                } else {
                    // Manejar respuesta de error
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                // Manejar error de la llamada
            }
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment perfil_usuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            perfil_usuario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}