package com.utad.tfg

data class PedidoModel(
    val idPedido: Int,
    val idUsuario: Int,
    val idEstablecimiento: Int,
    val fecha: String,
    val hora: String,
    val nombreProducto:String,
    val precio: String,
    val nombre: String,
    var talla:String
)
