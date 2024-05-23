package br.com.johabfreitas.recuperarimagensimgur.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.johabfreitas.recuperarimagensimgur.databinding.ItemGaleriaBinding
import com.squareup.picasso.Picasso

class GaleriaAdapter : Adapter<GaleriaAdapter.GaleriaViewHolder>() {


    private var listaImagens = emptyList<String>()
    fun adicionarImagens(lista : List<String>){
        this.listaImagens  = lista
        notifyDataSetChanged()
    }
    inner class GaleriaViewHolder(val binding: ItemGaleriaBinding) : ViewHolder(binding.root){

        fun bind(url: String){
            Picasso.get()
                .load(url)
                .into(binding.imgItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = ItemGaleriaBinding.inflate(layoutInflater, parent, false)
        return GaleriaViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val url = listaImagens[position]
        holder.bind( url)
    }

    override fun getItemCount(): Int {
        return listaImagens.size
    }
}