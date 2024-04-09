package ir.amirreza.sensormodule.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ir.amirreza.sensormodule.R
import ir.amirreza.sensormodule.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getAllSensors(context)
    }
    val sensors by viewModel.sensors.collectAsStateWithLifecycle()
    Column {
        SmallTopAppBar(title = { Text(text = "Logger") })
        LazyColumn {
            items(sensors, key = { it.id }) {
                ListItem(headlineContent = { Text(text = it.name) }, trailingContent = {
                    IconButton(onClick = {
                        navController.navigate("detail?id=" + it.id)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_remove_red_eye_24),
                            contentDescription = null
                        )
                    }
                })
            }
        }
    }
}