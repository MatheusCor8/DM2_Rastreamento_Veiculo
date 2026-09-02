package br.com.matheuscorreia.rastreamentoveiculo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import br.com.matheuscorreia.rastreamentoveiculo.ui.theme.RastreamentoVeiculoTheme
import br.com.matheuscorreia.rastreamentoveiculo.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RastreamentoVeiculoTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(auth = auth) {
                navController.navigate("home")
            }
        }
        composable("home") {
            HomeScreen(
                onNavigateToVehicle = { navController.navigate("register_vehicle") },
                onNavigateToDriver = { navController.navigate("register_driver") },
                onNavigateToProduct = { navController.navigate("register_product") },
                onNavigateToMerchant = { navController.navigate("register_merchant") },
                onNavigateToEvaluation = { navController.navigate("register_evaluation") },
                onLogout = {
                    auth.signOut()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
        composable("register_vehicle") {
            VehicleRegistrationScreen {
                navController.popBackStack()
            }
        }
        composable("register_driver") {
            DriverRegistrationScreen {
                navController.popBackStack()
            }
        }
        composable("register_product") {
            ProductRegistrationScreen {
                navController.popBackStack()
            }
        }
        composable("register_merchant") {
            MerchantRegistrationScreen {
                navController.popBackStack()
            }
        }
        composable("register_evaluation") {
            EvaluationRegistrationScreen {
                navController.popBackStack()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RastreamentoVeiculoTheme {
        LoginScreen(onLoginSuccess = {})
    }
}