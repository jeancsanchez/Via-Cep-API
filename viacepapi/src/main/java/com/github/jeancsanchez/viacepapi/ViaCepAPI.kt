package com.github.jeancsanchez.viacepapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/07/19.
 * Jesus is alive!
 */
@Suppress("SpellCheckingInspection")
internal interface ViaCepAPI {

    @GET("ws/{cep}/json/")
    fun buscarCep(@Path("cep") cep: Long): Call<Cep>

    @GET("ws/{uf}/{cidade}/{logradouro}/json/")
    fun buscarCepsPorEndereco(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("logradouro") logradouro: String
    ): Call<List<Cep>>
}