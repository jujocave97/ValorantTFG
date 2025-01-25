package com.example.valoranttfg.composable

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.valoranttfg.model.Ability
import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.room.AppDatabase
import com.example.valoranttfg.room.dao.AgentViewModel
import com.example.valoranttfg.room.entities.AgentEntity
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentSelected(agent: Agent, navController: NavController, agentViewModel: AgentViewModel){
    val gson = Gson()
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
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del título
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Color del icono de navegación
            )
        )

        // Contenido principal debajo del TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente todo el contenido
        ) {
            URLImage(
                modifier = Modifier.size(250.dp)
                    .background(color = MaterialTheme.colorScheme.background),
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
                text = agent.role.displayName,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Text(
                text = agent.role.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 16.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            Button(
                onClick = { val agentJson = Uri.encode(gson.toJson(agent)) // Codificar JSON
                    navController.navigate("Skills_Agent_Screen/$agentJson") },
                modifier = Modifier
                    .padding(16.dp) // Espaciado alrededor del botón
            ) {
                Text(text = "Habilidades")
            }
            Spacer(Modifier.padding(5.dp))
            Button(
                onClick = {
                    val agente = AgentEntity(
                        uuid = agent.uuid,
                        displayName = agent.displayName,
                        description = agent.description,
                        fullPortrait = agent.fullPortrait,
                        displayIcon = agent.displayIcon,
                        roleUuid = agent.role.uuid
                    )
                    agentViewModel.insertAgent(agente)
                },
                modifier = Modifier
                    .padding(16.dp) // Espaciado alrededor del botón
            ) {
                Text(text = "Guardar en Favoritos")
            }

        }
    }
}

