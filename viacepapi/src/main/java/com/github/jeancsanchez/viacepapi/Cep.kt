package com.github.jeancsanchez.viacepapi

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/07/19.
 * Jesus is alive!
 */
@Suppress("SpellCheckingInspection")
data class Cep(
    val cep: String,
    val bairro: String,
    val complemento: String,
    val gia: String,
    val ibge: String,
    val localidade: String,
    val logradouro: String,
    val uf: String,
    val unidade: String
)