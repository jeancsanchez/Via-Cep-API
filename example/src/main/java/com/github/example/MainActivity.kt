package com.github.example

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.github.jeancsanchez.viacepapi.ViaCepRequest
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/07/19.
 * Jesus is alive!
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCep?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ViaCepRequest().buscarCepsPorEndereco(
                    uf = "CE",
                    cidade = "Fortaleza",
                    logradouro = "Domingos",
                    onSuccess = { ceps ->
                    },
                    onError = {
                    })
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })
    }
}
