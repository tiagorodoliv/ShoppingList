package br.ifsp.shoppinglist.repository

import androidx.lifecycle.LiveData
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.data.ListaDAO

class ListaRepository (private val listaDAO: ListaDAO) {
    suspend fun insert(lista: Lista){
        listaDAO.insert(lista)
    }
    fun getAllContacts(): LiveData<List<Lista>> {
        return listaDAO.getAllContacts()
    }
}