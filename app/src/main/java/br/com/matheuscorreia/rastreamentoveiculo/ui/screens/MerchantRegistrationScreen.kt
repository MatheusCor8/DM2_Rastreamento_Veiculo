package br.com.matheuscorreia.rastreamentoveiculo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.matheuscorreia.rastreamentoveiculo.data.local.AppDatabase
import br.com.matheuscorreia.rastreamentoveiculo.data.model.Merchant
import kotlinx.coroutines.launch

@Composable
fun MerchantRegistrationScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)
    val merchants by db.merchantDao().getAllMerchants().collectAsState(initial = emptyList())

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cadastro de Negociante", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && address.isNotBlank() && phone.isNotBlank()) {
                    scope.launch {
                        db.merchantDao().insert(Merchant(name = name, address = address, phone = phone))
                        Toast.makeText(context, "Negociante salvo!", Toast.LENGTH_SHORT).show()
                        name = ""
                        address = ""
                        phone = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
            Text("Voltar")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Negociantes Cadastrados:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(merchants) { merchant ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nome: ${merchant.name}")
                        Text(text = "Endereço: ${merchant.address}")
                    }
                }
            }
        }
    }
}