package br.ifsp.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListaDAO {
    @Insert
    suspend fun insert(lista: Lista)

    @Query("SELECT * FROM lista ORDER BY nome")
    fun getAllContacts(): LiveData<List<Lista>>
}
