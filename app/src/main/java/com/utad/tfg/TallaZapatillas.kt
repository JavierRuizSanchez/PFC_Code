package com.utad.tfg

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TallaZapatillas : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_talla_zapatillas, container, false)

        val productImageView = view.findViewById<ImageView>(R.id.productImageZap)
        productImageView.setImageResource(DataHolder.selectedImageResource)

        val btnInicio:Button = view.findViewById(R.id.btnMenuTallaZapatillas)
        val talla1Button = view.findViewById<ToggleButton>(R.id.talla1)
        val talla2Button = view.findViewById<ToggleButton>(R.id.talla2)
        val talla3Button = view.findViewById<ToggleButton>(R.id.talla3)
        val talla4Button = view.findViewById<ToggleButton>(R.id.talla4)
        val talla5Button = view.findViewById<ToggleButton>(R.id.talla5)
        val talla6Button = view.findViewById<ToggleButton>(R.id.talla6)
        val talla7Button = view.findViewById<ToggleButton>(R.id.talla7)
        val talla8Button = view.findViewById<ToggleButton>(R.id.talla8)
        val talla9Button = view.findViewById<ToggleButton>(R.id.talla9)
        val talla10Button = view.findViewById<ToggleButton>(R.id.talla10)
        val talla11Button = view.findViewById<ToggleButton>(R.id.talla11)
        val talla12Button = view.findViewById<ToggleButton>(R.id.talla12)
        val añadirCestaZap = view.findViewById<Button>(R.id.AñadirCestaZap)

        btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_tallaZapatillas_to_establecimientos)
        }

        talla1Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
        }}

        talla2Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
        }}

        talla3Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
        }}

        talla4Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
        }}

        talla5Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla6Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla7Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla8Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla9Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla10Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla11Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        talla12Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            mostrarTallaAgregadaExitosamente(requireContext())
            }}

        añadirCestaZap.setOnClickListener {
            Toast.makeText(requireContext(), "Pedido añadido con éxito", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    fun mostrarTallaAgregadaExitosamente(context: Context) {
        Toast.makeText(context, "Talla seleccionada con éxito", Toast.LENGTH_SHORT).show()
    }
}
