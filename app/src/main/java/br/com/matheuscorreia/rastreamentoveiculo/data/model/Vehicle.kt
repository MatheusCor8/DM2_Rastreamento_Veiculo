package br.com.matheuscorreia.rastreamentoveiculo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val model: String,
    val plate: String,
    val status: String = "Disponível"
)