package com.utad.tfg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MisPedidosAdapter(private val mispedidos: List<Mispedidos>) :
    RecyclerView.Adapter<MisPedidosAdapter.MisPedidosViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MisPedidosViewHolder{
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_mispedidos, parent, false)
            return MisPedidosViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MisPedidosViewHolder, position: Int) {
            val pedido = mispedidos[position]
            holder.bind(pedido)
        }

        override fun getItemCount(): Int {
            return mispedidos.size
        }

        inner class MisPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val txtNombre: TextView = itemView.findViewById(R.id.nombrePedido)
            private val txtEstPed: TextView = itemView.findViewById(R.id.establecimientoPedido)

            fun bind(Mispedidos: Mispedidos) {
                // Asigna los valores a los elementos del CardView
                txtNombre.text = Mispedidos.nombre
                txtEstPed.text = Mispedidos.establecimiento

            }
        }
    }
