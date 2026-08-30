package br.com.matheuscorreia.poc.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

class AuthPocActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        
        setContent {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "PoC: Firebase Authentication")
                Text(text = if (user != null) "Status: Logado (${user.email})" else "Status: Não logado")
            }
        }
    }
}