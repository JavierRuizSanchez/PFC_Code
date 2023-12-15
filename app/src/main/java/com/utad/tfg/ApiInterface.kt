package com.utad.tfg

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("tienda/{id}")
    fun getTiendaById(@Path("id") id: Int): Call<TiendaModel>

    @GET("tienda")
    fun getTiendas(): Call<List<TiendaModel>>

    @GET("restaurante/{id}")
    fun getRestauranteById(@Path("id") id: Int): Call<RestauranteModel>

    @GET("restaurante")
    fun getRestaurantes(): Call<List<RestauranteModel>>

    @GET("pedidos/{id}")
    fun getPedidoById(@Path("id") id: Int): Call<PedidoModel>

    @GET("pedidos/establecimiento/{idEstablecimiento}")
    fun getPedidosByIdEstablecimiento(@Path("idEstablecimiento") idEstablecimiento: Int): Call<List<PedidoModel>>

    @GET("peluqueria/{id}")
    fun getPeluqueriaById(@Path("id") id: Int): Call<PeluqueríaModel>

    @GET("peluqueria")
    fun getPeluquerias(): Call<List<PeluqueríaModel>>

    @GET("reservas/{id}")
    fun getReservaById(@Path("id") id: Int): Call<ReservaModel>

    @GET("usuario/{id}")
    fun getUsuarioById(@Path("id") id: Int): Call<UsuarioModel>

    @POST("usuario")
    fun createUsuario(@Body usuario: UsuarioModel): Call<ApiResponse>

    @POST("pedidos")
    fun createPedido(@Body pedido: PedidoModel): Call<ApiResponse>

    @POST("reservas")
    fun createReserva(@Body reserva: ReservaModel): Call<ApiResponse>

    @GET("usuario/email/{email}")
    fun getUsuarioByEmail(@Path("email") email: String): Call<UsuarioModel>

    @GET("pedidos/nombre/{nombre}")
    fun getPedidosByNombre(@Path("nombre") nombre: String): Call<PedidoModel>

    @DELETE("pedidos/{id}")
    fun eliminarPedido(@Path("id") id: Int): Call<ApiResponse>

    @DELETE("pedidos/establecimiento/{idEstablecimiento}")
    fun deletePedidoByIdEstablecimiento(@Path("idEstablecimiento") idEstablecimiento: Int): Call<ApiResponse>

    @PUT("reservas/{id}")
    fun actualizarReserva(@Path("id") id: Int, @Body reserva: ReservaModel): Call<ApiResponse>

    @PUT("pedidos/{id}")
    fun updatePedido(@Path("id") id: Int, @Body pedido: PedidoModel): Call<ApiResponse>

    // Agrega otros métodos de la API aquí para los diferentes endpoints
}
