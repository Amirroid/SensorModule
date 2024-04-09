package ir.amirreza.sensormodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.amirreza.sensormodule.features.detail.DetailScreen
import ir.amirreza.sensormodule.ui.theme.SensorModuleTheme
import ir.amirreza.sensormodule.features.home.HomeScreen as HomeScreen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SensorModuleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen1(navController)
                        }
                        composable("detail?id={id}", arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )) {
                            val type = it.arguments?.getInt("id")!!
                            DetailScreen(type)
                        }
                    }
                }
            }
        }
    }
}