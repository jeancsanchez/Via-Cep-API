package com.github.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jeancsanchez.viacepapi.ViaCepRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        ViaCepRequest().buscarCepsPorEndereco(
            uf = "CE",
            cidade = "Fortaleza",
            logradouro = "Domingos",
            onSuccess = { ceps ->
            },
            onError = {
            })
    }
}
