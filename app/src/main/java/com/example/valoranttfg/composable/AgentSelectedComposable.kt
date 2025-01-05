package com.example.valoranttfg.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valoranttfg.model.Ability
import com.example.valoranttfg.model.Agent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentSelected(agent: Agent, navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        // Título y botón de regreso
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            title = {
                Box(modifier = Modifier.fillMaxWidth().offset(x = (-20).dp), contentAlignment = Alignment.Center) {
                    Text(text = agent.displayName,
                        style = MaterialTheme.typography.titleLarge) // Centrado del título
                }
            }
        )

        // Contenido principal debajo del TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente todo el contenido
        ) {
            URLImage(
                modifier = Modifier.size(250.dp),
                url = agent.fullPortrait,
                contentDescription = "Imagen de ${agent.displayName}"
            )
            Text(
                text = agent.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(10.dp))
            Text(
                text = "Habilidades",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            agent.abilities?.let {  // mostrar las habilidades del agente recorriendo el array de habilidades
                val habilidades = it

                LazyColumn {
                    items(habilidades) { map ->
                        Habilidad(map)
                    }
                }
            }

        }
    }
}

@Composable
fun Habilidad(ability: Ability){
    Box(
        modifier = Modifier
            .fillMaxWidth() // Asegura que el Box ocupe todo el ancho disponible
            .padding(16.dp), // Puedes ajustar el padding general según lo necesites
        contentAlignment = Alignment.Center // Centra todo el contenido dentro del Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centra todo en la columna
            verticalArrangement = Arrangement.Center, // Alinea verticalmente en el centro
            modifier = Modifier.fillMaxWidth() // Hace que la columna ocupe todo el ancho
        ) {
            Text(
                text = ability.displayName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(3.dp))
            URLImage(
                modifier = Modifier.size(175.dp),
                url = ability.displayIcon,
                contentDescription = "Imagen de ${ability.displayName}"
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text = ability.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
        }
    }
}