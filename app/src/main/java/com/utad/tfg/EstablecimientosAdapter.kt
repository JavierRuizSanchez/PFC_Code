package com.utad.tfg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EstablecimientoAdapter(private var establecimientos: List<Establecimiento>) :
    RecyclerView.Adapter<EstablecimientoAdapter.EstablecimientoViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null
    private var imageClickListener: OnImageClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstablecimientoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_establecimientos, parent, false)
        return EstablecimientoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EstablecimientoViewHolder, position: Int) {
        val establecimiento = establecimientos[position]
        holder.bind(establecimiento)
    }

    override fun getItemCount(): Int {
        return establecimientos.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun setOnImageClickListener(listener: OnImageClickListener) {
        imageClickListener = listener
    }

    fun setEstablecimientos(establecimientos: List<Establecimiento>) {
        this.establecimientos = establecimientos
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(establecimiento: Establecimiento)
    }

    interface OnImageClickListener {
        fun onImageClick(establecimiento: Establecimiento)
    }

    inner class EstablecimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageEstablecimiento: ImageView = itemView.findViewById(R.id.imageEstablecimiento)
        private val companyName: TextView = itemView.findViewById(R.id.companyName)
        private val txtUbicacion: TextView = itemView.findViewById(R.id.txtUbicacion)
        private val txtTelefono: TextView = itemView.findViewById(R.id.txtTelefono)
        private val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val establecimiento = establecimientos[position]
                    itemClickListener?.onItemClick(establecimiento)
                }
            }

            imageEstablecimiento.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val establecimiento = establecimientos[position]
                    imageClickListener?.onImageClick(establecimiento)
                }
            }
        }

        fun bind(establecimiento: Establecimiento) {
            // Asigna los valores a los elementos del CardView
            companyName.text = establecimiento.nombre
            txtUbicacion.text = establecimiento.ubicacion
            txtTelefono.text = establecimiento.telefono
            txtEmail.text = establecimiento.email

            Picasso.get().load(establecimiento.imageUrl).into(imageEstablecimiento)
        }
    }
}
