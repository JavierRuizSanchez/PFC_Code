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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ver_reservas_tienda : Fragment() {

    private lateinit var ver_NombreReservaPerTienda: EditText
    private lateinit var ver_horaReservaTienda: TimePicker
    private lateinit var fechaEditTextTienda: EditText

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
        val view = inflater.inflate(R.layout.fragment_ver_reservas_tienda, container, false)
        ver_NombreReservaPerTienda = view.findViewById(R.id.ver_NombreReservaPerTienda)
        ver_horaReservaTienda = view.findViewById(R.id.ver_horaReservaTienda)

        fechaEditTextTienda = view.findViewById(R.id.ver_FechaReservaTienda)
        fechaEditTextTienda.setOnClickListener {
            showDatePicker()
        }

        val btnModificarResTienda = view.findViewById<Button>(R.id.btnModificarResTienda)
        btnModificarResTienda.setOnClickListener {
            modificarReservaTienda()
            Toast.makeText(context, "Reserva modificada con éxito", Toast.LENGTH_SHORT).show()
        }

        val ver_NombreReservaPerTienda = view.findViewById<EditText>(R.id.ver_NombreReservaPerTienda)

        val apiInterface = ApiClient.apiService
        val idReserva = 8 // Reemplaza 1 con el idReserva correcto

        val obtenerReservaCall = apiInterface.getReservaById(idReserva)
        obtenerReservaCall.enqueue(object : Callback<ReservaModel> {
            override fun onResponse(call: Call<ReservaModel>, response: Response<ReservaModel>) {
                if (response.isSuccessful) {
                    val reserva = response.body()
                    val nombreReserva = reserva?.nombre
                    ver_NombreReservaPerTienda.setText(nombreReserva)
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

    private fun modificarReservaTienda() {
        val idReserva = 8 // Obtener el ID de la reserva que deseas modificar

        val nuevoNombreReserva = ver_NombreReservaPerTienda.text.toString()
        val nuevaHoraReserva = "${ver_horaReservaTienda.currentHour}:${ver_horaReservaTienda.currentMinute}"
        val nuevaFechaReserva = fechaEditTextTienda.text.toString()

        val apiInterface = ApiClient.apiService

        val actualizarReservaCall = apiInterface.actualizarReserva(
            idReserva,
            ReservaModel(
                idReserva,
                nuevoNombreReserva,
                nuevaHoraReserva,
                nuevaFechaReserva,
                1,  // Reemplaza 0 con el ID correcto del usuario
                3  // Reemplaza 0 con el ID correcto del establecimiento
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
                fechaEditTextTienda.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ver_reservas_tienda().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
