package br.com.matheuscorreia.rastreamentoveiculo.data.local

import androidx.room.*
import br.com.matheuscorreia.rastreamentoveiculo.data.model.Driver
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driver: Driver)

    @Query("SELECT * FROM drivers")
    fun getAllDrivers(): Flow<List<Driver>>

    @Delete
    suspend fun delete(driver: Driver)
}