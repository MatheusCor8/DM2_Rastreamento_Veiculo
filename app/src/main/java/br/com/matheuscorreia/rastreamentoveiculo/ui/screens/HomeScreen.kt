package br.com.matheuscorreia.rastreamentoveiculo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToVehicle: () -> Unit,
    onNavigateToDriver: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Menu Principal", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNavigateToVehicle,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Veículos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToDriver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Motoristas")
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sair")
        }
    }
}
