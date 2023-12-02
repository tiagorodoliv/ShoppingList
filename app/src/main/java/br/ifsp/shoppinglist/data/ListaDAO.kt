package br.ifsp.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ListaDAO {
    @Insert
    suspend fun insert(lista: Lista)

    @Update
    suspend fun update (lista: Lista)

    @Delete
    suspend fun delete(lista: Lista)

    @Query("SELECT * FROM lista ORDER BY nome")
    fun getAllContacts(): LiveData<List<Lista>>

    @Query("SELECT * FROM lista WHERE id=:id")
    fun getListaById(id: Int): LiveData<Lista>

}
