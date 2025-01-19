package com.example.valoranttfg.composable

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.valoranttfg.MainActivity
import com.example.valoranttfg.model.Team
import com.example.valoranttfg.service.recopilarEquipos
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionTeams(navController: NavController, region: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Título y botón de regreso
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth().offset(x = (-20).dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = region,
                        style = MaterialTheme.typography.titleLarge
                    ) // Centrado del título
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del título
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // Color del icono de navegación
            )
        )
        TeamList(region)
    }
}

@Composable
fun TeamList(region: String) {
    var teams by remember { mutableStateOf<List<Team>?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as MainActivity).lifecycleScope.launch {
            teams = recopilarEquipos(region)
        }
    }

    teams?.let { teamList ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(teamList) { team ->
                TeamItem(team = team)
            }
        }
    } ?: run {
        // Muestra un indicador de carga mientras se cargan los equipos
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}



@Composable
fun TeamItem(team: Team) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Imagen del equipo
            URLImage(
                modifier = Modifier
                    .size(200.dp)
                    .background(MaterialTheme.colorScheme.background),
                url = team.img,
                contentDescription = "Logo del equipo ${team.name}"
            )
            Spacer(Modifier.height(16.dp))

            // Nombre del equipo
            Text(
                text = team.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))

            // País del equipo
            Text(
                text = "País: ${team.country}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))

            // Botón para visitar el enlace
            Button(
                onClick = {  // Crear un Intent para abrir el navegador con la URL
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(team.url))
                    // Iniciar el intent
                    context.startActivity(intent) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Visitar Página")
            }
        }
    }
}

