package com.github.example

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jeancsanchez.viacepapi.Cep
import com.github.jeancsanchez.viacepapi.ViaCepRequest
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/07/19.
 * Jesus is alive!
 */
@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {
    private val viaCepRequest by lazy { ViaCepRequest() }
    private val adapter by lazy { CepAdapter() }
    private var uf = ""
    private var cidade = ""
    private var logradouro = ""
    private var preenchimentoAutomatico: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        edtCep?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isNotEmpty() == true && preenchimentoAutomatico.not()) {
                    val cepString = s.toString().replace("-", "")

                    viaCepRequest.buscarCep(
                        cep = cepString.toLong(),
                        onSuccess = { cep ->
                            cep?.let { preencherEndereco(it) }
                        },
                        onError = {
                        })
                } else {
                    limparEndereco()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })

        edtUf?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                uf = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        edtLocalidade?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cidade = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        edtLogradouro?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                logradouro = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                if (preenchimentoAutomatico.not()) {
                    viaCepRequest.buscarCepsPorEndereco(
                        uf = uf,
                        cidade = cidade,
                        logradouro = logradouro,
                        onSuccess = {
                            adapter.onClickListener = { cep ->
                                preencherEndereco(cep)
                            }
                            adapter.ceps = it
                        },
                        onError = {
                        }
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    private fun preencherEndereco(cep: Cep) {
        preenchimentoAutomatico = true
        edtCep?.setText(cep.cep)
        edtUf?.setText(cep.uf)
        edtLocalidade?.setText(cep.localidade)
        edtLogradouro?.setText(cep.logradouro)
        edtBairro?.setText(cep.bairro)
        edtComplemento?.setText(cep.complemento)
        preenchimentoAutomatico = false
    }

    private fun limparEndereco() {
        edtUf?.setText("")
        edtLocalidade?.setText("")
        edtLogradouro?.setText("")
        edtBairro?.setText("")
        edtComplemento?.setText("")
    }
}
