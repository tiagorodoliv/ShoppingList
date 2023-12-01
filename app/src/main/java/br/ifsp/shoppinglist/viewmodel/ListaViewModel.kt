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
    lateinit var lista : LiveData<Lista>
    var allList : LiveData<List<Lista>>
    init {
        val dao = ListaDatabase.getDatabase(application).ListaDAO()
        repository = ListaRepository(dao)
        allList = repository.getAllContacts()
    }
    fun insert(lista: Lista) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(lista)
    }
}