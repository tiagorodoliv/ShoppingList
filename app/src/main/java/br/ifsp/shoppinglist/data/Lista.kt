package br.ifsp.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lista (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nome:String,
    var unidade:Int,
    var descricao:String?,
    var setor:String?
)