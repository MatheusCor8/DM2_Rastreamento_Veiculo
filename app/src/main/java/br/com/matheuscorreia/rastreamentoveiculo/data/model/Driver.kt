package br.com.matheuscorreia.rastreamentoveiculo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class Driver(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val cpf: String,
    val vehicleId: Int? = null
)