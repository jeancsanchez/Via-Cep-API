package com.github.jeancsanchez.viacepapi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Based on https://viacep.com.br/
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/07/19.
 * Jesus is alive!
 */
@Suppress("SpellCheckingInspection")
class ViaCepRequest {

    private val api: ViaCepAPI by lazy {
        Retrofit.Builder()
            .baseUrl("http://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(ViaCepAPI::class.java) }
    }

    /**
     * Busca o cep pelo número.
     * @param cep Número do cep.
     * @param onSuccess Callback de sucesso.
     * @param onError Callback de erro.
     */
    fun buscarCep(
        cep: Long,
        onSuccess: (Cep?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api
            .buscarCep(cep)
            .enqueue(object : Callback<Cep> {
                override fun onResponse(call: Call<Cep?>, response: Response<Cep>) {
                    response.body()?.let { onSuccess(it) } ?: onSuccess(null)
                }

                override fun onFailure(call: Call<Cep?>, t: Throwable) {
                    onError(t)
                }
            })
    }

    /**
     * Buscar por ceps baseado em um endereço.
     * @param uf Sigla do estado. Exemplo: Ceará -> uf: CE.
     * @param cidade Nome da cidade. Exemplo: Fortaleza.
     * @param bairro Logradouro.
     * @param onSuccess Callback de sucesso.
     * @param onError Callback de erro.
     */
    fun buscarCepsPorEndereco(
        uf: String,
        cidade: String,
        logradouro: String,
        onSuccess: (List<Cep>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (uf.length == 2 && cidade.length > 3 && logradouro.length > 3) {
            api
                .buscarCepsPorEndereco(uf, cidade, logradouro)
                .enqueue(object : Callback<List<Cep>> {
                    override fun onResponse(call: Call<List<Cep>>, response: Response<List<Cep>>) {
                        response.body()?.let { onSuccess(it) } ?: onSuccess(emptyList())
                    }

                    override fun onFailure(call: Call<List<Cep>>, t: Throwable) {
                        onError(t)
                    }
                })
        }
    }
}