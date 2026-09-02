package br.com.matheuscorreia.rastreamentoveiculo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.matheuscorreia.rastreamentoveiculo.data.local.AppDatabase
import br.com.matheuscorreia.rastreamentoveiculo.data.model.Vehicle
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun VehicleRegistrationScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)
    val vehicles by db.vehicleDao().getAllVehicles().collectAsState(initial = emptyList())

    var model by remember { mutableStateOf("") }
    var plate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cadastro de Veículo", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Modelo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = plate,
            onValueChange = { plate = it },
            label = { Text("Placa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (model.isNotBlank() && plate.isNotBlank()) {
                    scope.launch {
                        db.vehicleDao().insert(Vehicle(model = model, plate = plate))
                        Toast.makeText(context, "Veículo salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        model = ""
                        plate = ""
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Veículos Cadastrados:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vehicles) { vehicle ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Modelo: ${vehicle.model}")
                        Text(text = "Placa: ${vehicle.plate}")
                    }
                }
            }
        }
    }
}
