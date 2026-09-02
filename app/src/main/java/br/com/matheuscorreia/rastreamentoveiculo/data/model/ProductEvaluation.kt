package br.com.matheuscorreia.rastreamentoveiculo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_evaluations")
data class ProductEvaluation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val rating: Int,
    val comment: String
)