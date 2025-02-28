package com.example.valoranttfg.composable

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.room.dao.AgentViewModel
import com.example.valoranttfg.room.entities.AgentEntity
import com.google.gson.Gson
import com.example.valoranttfg.composable.AgentEntityItem as AgentEntityItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsDBScreen(agentViewModel: AgentViewModel, navController: NavController) {
    // Observamos la lista de agentes desde el ViewModel
    val agents: List<AgentEntity> by agentViewModel.getAllAgents().observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agentes Favoritos",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 40.dp, 0.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Home_Screen") }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                    titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del título
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Color del icono de navegación
                )
            )
        }
    ) {
        paddingValues -> // Aquí pasamos paddingValues a nuestro contenido
        Column(modifier = Modifier.padding(paddingValues)) {
            // Mostrar el primer agente fuera del Scaffold si existe
            if (agents.isEmpty()) { // Verifica si la lista está vacía
                // Muestra un mensaje si la lista está vacía
                Text(
                    text = "No hay agentes guardados en favoritos.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            } else {
                // Usamos LazyColumn para recorrer la lista de agentes (sin el primero)
                LazyColumn {
                    // Omite el primer agente de la lista, ya que lo mostramos arriba
                    items(agents) { agent ->
                        AgentEntityItem(agent = agent, agentViewModel = agentViewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun AgentEntityItem(agent: AgentEntity, agentViewModel: AgentViewModel) {
    val gson = Gson()
    Box(
        modifier = Modifier
            .fillMaxSize()  // Toma todo el tamaño disponible
            .padding(20.dp)
            .clickable {
                 // Codificar JSON
                 }, // Asegura algo de espacio alrededor
        contentAlignment = Alignment.Center, // Centra el contenido
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = agent.displayName, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.padding(5.dp))
            URLImage(modifier = Modifier.size(250.dp), url = agent.displayIcon, contentDescription = "Imagen de Agente")
            Button(
                onClick = {
                    agentViewModel.deleteAgent(agent.uuid)
                },
                modifier = Modifier
                    .padding(16.dp) // Espaciado alrededor del botón
            ) {
                Text(text = "Eliminar Agente de Favoritos")
            }
        }
    }
}

