package com.utad.tfg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MisReservasAdapter (private val misreservas: List<Misreservas>) :
    RecyclerView.Adapter<MisReservasAdapter.MisReservasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MisReservasViewHolder{
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_misreservas, parent, false)
        return MisReservasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MisReservasViewHolder, position: Int) {
        val reserva = misreservas[position]
        holder.bind(reserva)
    }

    override fun getItemCount(): Int {
        return misreservas.size
    }

    inner class MisReservasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNombre: TextView = itemView.findViewById(R.id.nombreReserva)
        private val txtEstPed: TextView = itemView.findViewById(R.id.establecimientoReserva)

        fun bind(Mispedidos: Misreservas) {
            // Asigna los valores a los elementos del CardView
            txtNombre.text = Mispedidos.nombre
            txtEstPed.text = Mispedidos.establecimiento

        }
    }
}