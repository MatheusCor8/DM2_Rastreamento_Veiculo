package br.com.matheuscorreia.rastreamentoveiculo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "merchants")
data class Merchant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val address: String,
    val phone: String
)