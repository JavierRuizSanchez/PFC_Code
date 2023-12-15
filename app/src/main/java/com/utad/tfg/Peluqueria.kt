package com.utad.tfg

import android.annotation.SuppressLint
import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.*

class Peluqueria : Fragment() {

    private lateinit var fechaEditText: EditText
    private lateinit var nombrePeluqueriaEditText: EditText
    private lateinit var horaPeluqueriaPicker: TimePicker

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_peluqueria, container, false)

        val MisResPel = view.findViewById<Button>(R.id.MisReservasPelIrRes)
        val btnInicio: Button = view.findViewById(R.id.btnMenuReservaPel)
        fechaEditText = view.findViewById(R.id.FechaPeluqueria)
        fechaEditText.setOnClickListener {
            showDatePicker()
        }

        btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_peluqueria_to_establecimientos)
        }

        MisResPel.setOnClickListener {
            findNavController().navigate(R.id.action_peluqueria_to_tiposReserva)
        }
        nombrePeluqueriaEditText = view.findViewById(R.id.NombrePeluqueria)
        horaPeluqueriaPicker = view.findViewById(R.id.horaPeluqueria)

        val hacerReservaButton: Button = view.findViewById(R.id.HacerReserva_peluqueria)
        hacerReservaButton.setOnClickListener {
            realizarReserva()
        }

        return view
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

    private fun realizarReserva() {
        val fechaReserva = fechaEditText.text.toString()
        val nombrePeluqueria = nombrePeluqueriaEditText.text.toString()
        val horaPeluqueria = "${horaPeluqueriaPicker.hour}:${horaPeluqueriaPicker.minute}"

        val reserva = ReservaModel(
            reservaId = 0,
            nombre = nombrePeluqueria,
            hora = horaPeluqueria,
            fecha = fechaReserva,
            usuarioId = 5, // Reemplaza con el ID de usuario real
            establecimientoId = 1 // Reemplaza con el ID de establecimiento real
        )

        val apiInterface = ApiClient.apiService
        val reservaCall = apiInterface.createReserva(reserva)
        reservaCall.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Reserva realizada con éxito",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Realizar las acciones necesarias después de realizar la reserva exitosamente
                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Error al realizar la reserva",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // Realizar las acciones necesarias en caso de error al realizar la reserva
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                activity?.runOnUiThread {
                    Toast.makeText(
                        requireContext(),
                        "Error al realizar la reserva",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Realizar las acciones necesarias en caso de error de conexión o fallo en la llamada a la API
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Peluqueria()
    }
}


