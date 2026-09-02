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
import br.com.matheuscorreia.rastreamentoveiculo.data.model.ProductEvaluation
import kotlinx.coroutines.launch

@Composable
fun EvaluationRegistrationScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = AppDatabase.getDatabase(context)
    val evaluations by db.evaluationDao().getAllEvaluations().collectAsState(initial = emptyList())

    var productId by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Avaliação de Produto", style = MaterialTheme.typography.headlineMedium)
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productId,
            onValueChange = { productId = it },
            label = { Text("ID do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Nota (1-5)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comentário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val pId = productId.toIntOrNull()
                val r = rating.toIntOrNull()
                if (pId != null && r != null && comment.isNotBlank()) {
                    scope.launch {
                        db.evaluationDao().insert(ProductEvaluation(productId = pId, rating = r, comment = comment))
                        Toast.makeText(context, "Avaliação salva!", Toast.LENGTH_SHORT).show()
                        productId = ""
                        rating = ""
                        comment = ""
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
        Text(text = "Avaliações Cadastradas:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(evaluations) { evaluation ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Produto ID: ${evaluation.productId}")
                        Text(text = "Nota: ${evaluation.rating}")
                        Text(text = "Comentário: ${evaluation.comment}")
                    }
                }
            }
        }
    }
}