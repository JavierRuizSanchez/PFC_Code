package com.utad.tfg

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ver_reservas.newInstance] factory method to
 * create an instance of this fragment.
 */
class ver_reservas : Fragment() {

    private lateinit var ver_NombreReservaPer: EditText
    private lateinit var ver_horaReserva: TimePicker

    private lateinit var fechaEditText: EditText

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
        val view = inflater.inflate(R.layout.fragment_ver_reservas, container, false)
        ver_NombreReservaPer = view.findViewById(R.id.ver_NombreReservaPer)
        ver_horaReserva = view.findViewById(R.id.ver_horaReserva)

        fechaEditText = view.findViewById(R.id.ver_FechaReserva)
        fechaEditText.setOnClickListener {
            showDatePicker()
        }

        val btnModificarRes = view.findViewById<Button>(R.id.btnModificarRes)
        btnModificarRes.setOnClickListener {
            modificarReserva()
            Toast.makeText(context, "Reserva modificada con éxito", Toast.LENGTH_SHORT).show()
        }

        val ver_NombreReservaPer = view.findViewById<EditText>(R.id.ver_NombreReservaPer)

        val apiInterface = ApiClient.apiService
        val idReserva = 7 // Reemplaza 1 con el idReserva correcto

        val obtenerReservaCall = apiInterface.getReservaById(idReserva)
        obtenerReservaCall.enqueue(object : Callback<ReservaModel> {
            override fun onResponse(call: Call<ReservaModel>, response: Response<ReservaModel>) {
                if (response.isSuccessful) {
                    val reserva = response.body()
                    val nombreReserva = reserva?.nombre
                    ver_NombreReservaPer.setText(nombreReserva)
                } else {
                    Toast.makeText(context, "No se ha podido modificar la reserva", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReservaModel>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })


        return view
    }

    private fun modificarReserva() {
        val idReserva = 7// Obtener el ID de la reserva que deseas modificar

        val nuevoNombreReserva = ver_NombreReservaPer.text.toString()
        val nuevaHoraReserva = "${ver_horaReserva.currentHour}:${ver_horaReserva.currentMinute}"
        val nuevaFechaReserva = fechaEditText.text.toString()

        val apiInterface = ApiClient.apiService

        val actualizarReservaCall = apiInterface.actualizarReserva(
            idReserva,
            ReservaModel(
                idReserva,
                nuevoNombreReserva,
                nuevaHoraReserva,
                nuevaFechaReserva,
                1,  // Reemplaza 0 con el ID correcto del usuario
                1  // Reemplaza 0 con el ID correcto del establecimiento
            )
        )

        actualizarReservaCall.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    // Reserva actualizada correctamente
                } else {
                    // Manejar el error de respuesta
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                fechaEditText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ver_reservas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ver_reservas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}