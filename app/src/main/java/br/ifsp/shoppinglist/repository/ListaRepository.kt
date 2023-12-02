package br.ifsp.shoppinglist.repository

import androidx.lifecycle.LiveData
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.data.ListaDAO

class ListaRepository (private val contatoDAO: ListaDAO) {

    suspend fun insert(lista: Lista){
        contatoDAO.insert(lista)
    }

    suspend fun update(lista: Lista){
        contatoDAO.update(lista)
    }

    suspend fun delete(lista: Lista){
        contatoDAO.delete(lista)
    }

    fun getAllLista(): LiveData<List<Lista>> {
        return contatoDAO.getAllContacts()
    }

    fun getContactById(id: Int): LiveData<Lista>{
        return contatoDAO.getListaById(id)
    }

}