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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HacerReservaRestaurante.newInstance] factory method to
 * create an instance of this fragment.
 */
class HacerReservaRestaurante : Fragment() {

    private lateinit var fechaEditText: EditText
    private lateinit var nombreReservaEditText: EditText
    private lateinit var horaReservaPicker: TimePicker

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
        val view = inflater.inflate(R.layout.fragment_hacer_reserva_restaurante, container, false)

        val MisResIrRes: Button = view.findViewById(R.id.MisReservasIrRes)
        val productSpinner: Spinner = view.findViewById(R.id.SpinnerPersonas)
        val productList = listOf("1", "2", "3", "4", "5", "6")
        val productAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, productList)
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productSpinner.adapter = productAdapter

        MisResIrRes.setOnClickListener {
            findNavController().navigate(R.id.action_hacerReservaRestaurante_to_tiposReserva)
        }
        fechaEditText = view.findViewById(R.id.FechaReserva)
        fechaEditText.setOnClickListener {
            showDatePicker()
        }

        nombreReservaEditText = view.findViewById(R.id.NombreReservaPer)
        horaReservaPicker = view.findViewById(R.id.horaReserva)

        val hacerReservaButton: Button = view.findViewById(R.id.HacerReserva_restaurante)
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
        val nombreReserva = nombreReservaEditText.text.toString()
        val horaReserva = "${horaReservaPicker.hour}:${horaReservaPicker.minute}"

        val reserva = ReservaModel(
            reservaId = 0,
            nombre = nombreReserva,
            hora = horaReserva,
            fecha = fechaReserva,
            usuarioId = 1, // Reemplaza con el ID de usuario real
            establecimientoId = 1 // Reemplaza con el ID de establecimiento real
        )

        // Realizar la llamada a la API para crear la reserva
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
        fun newInstance() = HacerReservaRestaurante()
    }
}
