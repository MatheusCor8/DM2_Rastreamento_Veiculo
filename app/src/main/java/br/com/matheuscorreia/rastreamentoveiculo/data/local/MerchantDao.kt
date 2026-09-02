package br.com.matheuscorreia.rastreamentoveiculo.data.local

import androidx.room.*
import br.com.matheuscorreia.rastreamentoveiculo.data.model.Merchant
import kotlinx.coroutines.flow.Flow

@Dao
interface MerchantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(merchant: Merchant)

    @Query("SELECT * FROM merchants")
    fun getAllMerchants(): Flow<List<Merchant>>

    @Delete
    suspend fun delete(merchant: Merchant)
}