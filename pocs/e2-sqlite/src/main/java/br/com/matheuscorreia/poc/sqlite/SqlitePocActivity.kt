package br.com.matheuscorreia.poc.sqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Entity
data class PocUser(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String)

@Dao
interface PocUserDao {
    @Insert
    suspend fun insert(user: PocUser)
    @Query("SELECT * FROM PocUser")
    fun getAll(): Flow<List<PocUser>>
}

@Database(entities = [PocUser::class], version = 1)
abstract class PocDatabase : RoomDatabase() {
    abstract fun userDao(): PocUserDao
}

class SqlitePocActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val db = Room.databaseBuilder(applicationContext, PocDatabase::class.java, "poc_db").build()
        
        setContent {
            val users by db.userDao().getAll().collectAsState(initial = emptyList())
            val scope = rememberCoroutineScope()

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "PoC Room (SQLite)")
                Text(text = "Usuários no Banco:")
                users.forEach { Text(text = "- ${it.name}") }
                
                androidx.compose.material3.Button(onClick = {
                    scope.launch {
                        db.userDao().insert(PocUser(name = "User ${users.size + 1}"))
                    }
                }) {
                    Text("Adicionar Usuário")
                }
            }
        }
    }
}