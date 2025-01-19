package com.example.valoranttfg.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsComposable(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Equipos",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del título
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Color del icono de navegación
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/eu")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Europa")
                }

                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/na")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("America del Norte")
                }

                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/br")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Brasil")
                }
                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/ch")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("China")
                }
                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/jp")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Japón")
                }
                Button(
                    onClick = {navController.navigate("Region_Teams_Screen/las")},
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Latinoamérica")
                }
            }
        }
    )
}