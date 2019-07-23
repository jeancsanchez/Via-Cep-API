package com.github.example

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.jeancsanchez.viacepapi.Cep
import com.github.jeancsanchez.viacepapi.ViaCepRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        ViaCepRequest().run {
            buscarCep(60752310,
                onSuccess = { cep: Cep? ->
                    txtLogradouro?.text = cep?.logradouro ?: "Não encontrado!"
                },
                onError = {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })
        }
    }
}
