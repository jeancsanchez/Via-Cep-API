package com.github.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jeancsanchez.viacepapi.Cep

/**
 * This class represents...
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 24/07/19.
 * Jesus is alive!
 */
@Suppress("SpellCheckingInspection")
class CepAdapter : RecyclerView.Adapter<CepAdapter.VH>() {
    var ceps: List<Cep> = emptyList()
        set(value) {
            field = value
            field.sortedBy { it.bairro }
            notifyDataSetChanged()
        }

    var onClickListener: ((Cep) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cep, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int = ceps.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (ceps.isNotEmpty()) {
            val cep = ceps[position]
            holder.txtLogradouro?.text = cep.logradouro
            holder.txtBairro?.text = cep.bairro
            holder.itemView.setOnClickListener {
                onClickListener?.invoke(cep)
                (ceps as ArrayList).clear()
                notifyDataSetChanged()
            }
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val txtBairro: TextView? = view.findViewById(R.id.txtBairroItem)
        val txtLogradouro: TextView? = view.findViewById(R.id.txtLogradouroItem)
    }
}