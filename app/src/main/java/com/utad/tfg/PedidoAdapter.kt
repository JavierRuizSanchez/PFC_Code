package com.utad.tfg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidoAdapter (private val onPedidoClickListener: OnPedidoClickListener) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    private var pedidos: List<PedidoModel> = emptyList()

    var pedidoSeleccionado: PedidoModel? = null

    interface OnPedidoClickListener {
        fun onPedidoClick(pedido: PedidoModel)
    }

    constructor() : this(object : OnPedidoClickListener {
        override fun onPedidoClick(pedido: PedidoModel) {
            // Por ahora, no necesitas realizar ninguna acción especial aquí
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)

        holder.itemView.setOnClickListener {
            // Establecer el pedido seleccionado al hacer clic en un elemento
            pedidoSeleccionado = pedido
            notifyDataSetChanged() // Notificar cambios para actualizar la apariencia

        }

        // Marcar visualmente el elemento seleccionado
        if (pedido == pedidoSeleccionado) {
            holder.itemView.setBackgroundResource(R.color.colorItemSelected)
        } else {
            holder.itemView.setBackgroundResource(android.R.color.transparent)
        }
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    fun setPedidos(pedidos: List<PedidoModel>) {
        this.pedidos = pedidos
        notifyDataSetChanged()
    }

    inner class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreProductoTextView: TextView = itemView.findViewById(R.id.nombreProducto)

        // Añade aquí los otros campos de pedido que deseas mostrar
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pedido = pedidos[position]
                    onPedidoClickListener.onPedidoClick(pedido)
                }
            }
        }

        fun bind(pedido: PedidoModel) {
            nombreProductoTextView.text = pedido.nombreProducto

            // Configura los otros campos del pedido
        }
    }
}
