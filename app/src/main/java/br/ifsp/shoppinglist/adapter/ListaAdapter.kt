package br.ifsp.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.databinding.ListaCelulaBinding

class ListaAdapter() : RecyclerView.Adapter<ListaAdapter.ListaViewHolder>(),
    Filterable {
    private lateinit var binding: ListaCelulaBinding
    var listaLista = ArrayList<Lista>()
    var listener: ListaListener? = null
    var listaListaFilterable = ArrayList<Lista>()

    fun updateList(newList: ArrayList<Lista>) {
        listaLista = newList
        listaListaFilterable = listaLista
        notifyDataSetChanged()
    }

    fun setClickListener(listener: ListaListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListaViewHolder {
        binding = ListaCelulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        holder.nomeVH.text = listaLista[position].nome
        holder.unidadeVH.text = listaLista[position].unidade.toString()
    }

    override fun getItemCount(): Int {
        return listaLista.size
    }

    inner class ListaViewHolder(view: ListaCelulaBinding) : RecyclerView.ViewHolder(view.root) {
        val nomeVH = view.nome
        val unidadeVH = view.unidade

        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface ListaListener {
        fun onItemClick(pos: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty())
                    listaListaFilterable = listaLista
                else {
                    val resultList = ArrayList<Lista>()
                    for (row in listaLista)
                        if (row.nome.lowercase().contains(p0.toString().lowercase()))
                            resultList.add(row)
                    listaListaFilterable = resultList
                }
                val filterResults = FilterResults()

                filterResults.values = listaListaFilterable
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listaListaFilterable = p1?.values as ArrayList<Lista>
                notifyDataSetChanged()
            }
        }
    }
}
