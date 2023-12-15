package com.utad.tfg

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CrearCuenta.newInstance] factory method to
 * create an instance of this fragment.
 */
class CrearCuenta : Fragment() {

    private lateinit var textErr: TextView

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crear_cuenta, container, false)
        val textName = view.findViewById<EditText>(R.id.editTextNombre)
        val textMail = view.findViewById<EditText>(R.id.editTextEmail)
        val textConMail = view.findViewById<EditText>(R.id.editTextConfirmEmail)
        val textPass = view.findViewById<EditText>(R.id.editTextContraseña)
        val textConPass = view.findViewById<EditText>(R.id.editTextConfirmacionContraseña)
        val textTel = view.findViewById<EditText>(R.id.editTextTelefono)
        textErr = view.findViewById<TextView>(R.id.txtErrorCuenta)
        val btnCrCuenta = view.findViewById<Button>(R.id.btnCrearPerfil)
        btnCrCuenta.setOnClickListener {
            if(textMail.text.toString().isEmpty()){
                textErr.text = "Mail is required"
                return@setOnClickListener
            }
            if(textConMail.text.toString().isEmpty()){
                textErr.text = "Email confirmation is required"
                return@setOnClickListener
            }
            if(textPass.text.toString().isEmpty()){
                textErr.text = "Password is required"
                return@setOnClickListener
            }
            if(textConPass.text.toString().isEmpty()){
                textErr.text = "Password confirmation is required"
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(textMail.text.toString()).matches()){
                textErr.text = "The mail is invalid"
                return@setOnClickListener
            }
            if(textMail.text.toString() != textConMail.text.toString()){
                textErr.text = "Mails do not match"
                return@setOnClickListener
            }
            if(textPass.text.toString().length < 6){
                textErr.text = "The password must be at least 6 characters long"
                return@setOnClickListener
            }
            if(textPass.text.toString() != textConPass.text.toString()){
                textErr.text = "Passwords do not match"
                return@setOnClickListener
            }

            createUserAccount(textName.text.toString(), textMail.text.toString(), textPass.text.toString(), textTel.text.toString())

        }
        return view
    }

    private fun createUserAccount(nombre: String, email: String, contraseña: String, telefono: String) {
        val apiInterface = ApiClient.apiService
        val call = apiInterface.createUsuario(UsuarioModel(0, nombre, email, contraseña, telefono))

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_crearCuenta_to_establecimientos)
                } else {
                    // Error al insertar
                    val error = response.errorBody()?.string()
                    textErr.text = "Error al crear la cuenta: $error"
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Error en la llamada
                textErr.text = "Error en la llamada al servidor: ${t.message}"
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CrearCuenta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}