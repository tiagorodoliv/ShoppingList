package br.ifsp.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lista::class], version = 1)
abstract class ListaDatabase: RoomDatabase() {
    abstract fun ListaDAO(): ListaDAO
    companion object {
        @Volatile
        private var INSTANCE: ListaDatabase? = null
        fun getDatabase(lista: Application): ListaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    lista.applicationContext,
                    ListaDatabase::class.java,
                    "lista.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
