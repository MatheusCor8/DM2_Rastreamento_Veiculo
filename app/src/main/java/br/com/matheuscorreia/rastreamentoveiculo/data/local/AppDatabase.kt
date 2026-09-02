package br.com.matheuscorreia.rastreamentoveiculo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.matheuscorreia.rastreamentoveiculo.data.model.*

@Database(
    entities = [Vehicle::class, Driver::class, Product::class, Merchant::class, ProductEvaluation::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun driverDao(): DriverDao
    abstract fun productDao(): ProductDao
    abstract fun merchantDao(): MerchantDao
    abstract fun evaluationDao(): EvaluationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}