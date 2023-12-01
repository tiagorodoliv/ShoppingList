package br.ifsp.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.ListaCelulaBinding

class ListaAdapter():
    RecyclerView.Adapter<ListaAdapter.ContatoViewHolder>() {
    private lateinit var binding: ListaCelulaBinding
    var listaLista = ArrayList<Lista>()
    fun updateList(newList: ArrayList<Lista> ){
        listaLista = newList
        var listaListaFilterable = listaLista
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContatoViewHolder {
        binding = ListaCelulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContatoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        holder.nomeVH.text = listaLista[position].nome
        holder.unidadeVH.text = listaLista[position].unidade.toString()
    }
    override fun getItemCount(): Int {
        return listaLista.size
    }
    inner class ContatoViewHolder(view:ListaCelulaBinding): RecyclerView.ViewHolder(view.root)
    {
        val nomeVH = view.nome
        val unidadeVH = view.unidade
    }
}