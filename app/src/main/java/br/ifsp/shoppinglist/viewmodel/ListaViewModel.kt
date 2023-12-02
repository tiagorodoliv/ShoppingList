package br.ifsp.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.ifsp.shoppinglist.data.Lista
import br.ifsp.shoppinglist.data.ListaDatabase
import br.ifsp.shoppinglist.repository.ListaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ListaRepository
    var allLista : LiveData<List<Lista>>
    lateinit var lista : LiveData<Lista>

    init {
        val dao = ListaDatabase.getDatabase(application).listaDAO()
        repository = ListaRepository(dao)
        allLista = repository.getAllLista()
    }

    fun insert(lista: Lista) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(lista)
    }

    fun update(lista: Lista) = viewModelScope.launch(Dispatchers.IO){
        repository.update(lista)
    }

    fun delete(lista: Lista) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(lista)
    }


    fun getListaById(id: Int) {
        viewModelScope.launch {
            lista = repository.getContactById(id)
        }


    }
}