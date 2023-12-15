package com.utad.tfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class IniciarSesion : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false)
        val textEmail = view.findViewById<EditText>(R.id.TextEmail)
        val textPass = view.findViewById<EditText>(R.id.TextPassword)
        val textErr = view.findViewById<TextView>(R.id.txtError)
        val btnInSes = view.findViewById<Button>(R.id.buttonLogin)

        btnInSes.setOnClickListener {
            val email = textEmail.text.toString()
            val password = textPass.text.toString()

            // Verifica si el usuario y la contraseña son "admin"
            if (email == "admin@gmail.com" && password == "admin") {
                // Navega al fragmento del panel de administrador
                findNavController().navigate(R.id.action_iniciarSesion_to_adminPanelFragment)
            } else {
                // Si no es "admin", realiza la verificación normal
                val apiService = ApiClient.apiService
                val call = apiService.getUsuarioByEmail(email)
                call.enqueue(object : Callback<UsuarioModel> {
                    override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                        if (response.isSuccessful) {
                            val usuario = response.body()
                            if (usuario != null && usuario.email == email && usuario.contraseña == password) {
                                // Las credenciales son válidas
                                findNavController().navigate(R.id.action_iniciarSesion_to_establecimientos)
                            } else {
                                textErr.text = "Invalid email or password"
                            }
                        } else {
                            textErr.text = "Error occurred"
                        }
                    }

                    override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                        textErr.text = "Error occurred"
                    }
                })
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IniciarSesion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
