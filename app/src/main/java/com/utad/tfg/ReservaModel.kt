package com.utad.tfg

data class ReservaModel(
    val reservaId: Int,
    val nombre: String,
    val hora: String,
    val fecha: String,
    val usuarioId: Int,
    val establecimientoId: Int
)
