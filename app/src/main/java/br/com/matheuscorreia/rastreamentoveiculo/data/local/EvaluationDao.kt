package br.com.matheuscorreia.rastreamentoveiculo.data.local

import androidx.room.*
import br.com.matheuscorreia.rastreamentoveiculo.data.model.ProductEvaluation
import kotlinx.coroutines.flow.Flow

@Dao
interface EvaluationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evaluation: ProductEvaluation)

    @Query("SELECT * FROM product_evaluations")
    fun getAllEvaluations(): Flow<List<ProductEvaluation>>

    @Delete
    suspend fun delete(evaluation: ProductEvaluation)
}