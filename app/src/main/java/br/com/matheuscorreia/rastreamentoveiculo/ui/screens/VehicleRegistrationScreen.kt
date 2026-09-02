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

@Composable
fun VehicleRegistrationScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)

    var model by remember { mutableStateOf("") }
    var plate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Veículo", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Modelo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = plate,
            onValueChange = { plate = it },
            label = { Text("Placa") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (model.isNotBlank() && plate.isNotBlank()) {
                    scope.launch {
                        db.vehicleDao().insert(Vehicle(model = model, plate = plate))
                        Toast.makeText(context, "Veículo salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        onNavigateBack()
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}