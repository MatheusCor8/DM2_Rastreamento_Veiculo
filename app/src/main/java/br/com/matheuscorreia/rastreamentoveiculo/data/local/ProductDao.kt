package br.com.matheuscorreia.rastreamentoveiculo.data.local

import androidx.room.*
import br.com.matheuscorreia.rastreamentoveiculo.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    @Delete
    suspend fun delete(product: Product)
}