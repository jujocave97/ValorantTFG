package com.example.valoranttfg.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.valoranttfg.MainActivity
import com.example.valoranttfg.model.Match
import com.example.valoranttfg.model.Team
import com.example.valoranttfg.service.recopilarEquipos
import com.example.valoranttfg.service.recopilarMatchs
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchScreen(navController: NavController) {
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
                        text = "Games",
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
        MatchList()
    }
}
@Composable
fun MatchList() {
    var matchs by remember { mutableStateOf<List<Match>?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as MainActivity).lifecycleScope.launch {
            matchs = recopilarMatchs()
        }
    }

    matchs?.let { matchList ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(matchList) { match ->
                MatchItem(match = match)
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
fun MatchItem(match: Match) {
    Column(modifier = Modifier.padding(16.dp)) {
        URLImage(
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.background),
            url = match.img,
            contentDescription = "Imagen del Game"
        )
        Spacer(Modifier.height(10.dp))
        Text(text = "Evento: ${match.event}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Torneo: ${match.tournament}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Estado: ${match.status}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Tiempo: ${match.ago}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        match.teams.forEach { team ->
            Row {
                Text(text = "${team.name} - ${team.score} - ${team.country}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(8.dp))
                if (team.won) {
                    Text(text = "Ganador", color = MaterialTheme.colorScheme.primary)
                } else {
                    Text(text = "Perdedor", color = MaterialTheme.colorScheme.secondary)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Divider()
    }
}
