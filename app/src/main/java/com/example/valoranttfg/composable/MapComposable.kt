package com.example.valoranttfg.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.valoranttfg.MainActivity
import com.example.valoranttfg.model.Mapv
import com.example.valoranttfg.service.recopilarMapas
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullMapsScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") } // Estado del texto de búsqueda
    var isSearching by remember { mutableStateOf(false) } // Estado para controlar si el buscador está activo

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Buscar mapa...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = "Mapas",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Home_Screen") }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (!isSearching) {
                        IconButton(onClick = { isSearching = true }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
                        }
                    } else {
                        IconButton(onClick = {
                            isSearching = false
                            searchQuery = "" // Limpiar la búsqueda al cerrar
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar búsqueda")
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                MapListScreen(searchQuery = searchQuery)
            }
        }
    )
}

@Composable
fun MapListScreen(searchQuery: String) {
    var maps by remember { mutableStateOf<List<Mapv>?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as MainActivity).lifecycleScope.launch {
            maps = recopilarMapas()
        }
    }

    maps?.let {
        val filteredMaps = it.filter { map ->
            map.displayName.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn {
            items(filteredMaps) { map ->
                MapItem(map)
            }
        }
    }
}

@Composable
fun MapItem(map: Mapv) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = map.displayName, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.padding(5.dp))
        URLImageMap(
            modifier = Modifier.size(250.dp),
            url = map.splash,
            contentDescription = "Imagen de mapa"
        )
        Spacer(Modifier.padding(5.dp))
        Text(text = map.coordinates ?: "Coordenadas Confidenciales")
        Spacer(Modifier.padding(15.dp))
    }
}

@Composable
fun URLImageMap(
    modifier: Modifier = Modifier,
    url: String,
    colorFilter: ColorFilter? = null,
    birdDiscovered: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop,
    modifierColumn: Modifier = Modifier,
    elevation: Dp = 13.dp,
    contentDescription: String
) {
    val painter = rememberAsyncImagePainter(url)
    if (painter.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator()
    }

    Column(
        modifier = modifierColumn.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = elevation
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = modifier.fillMaxHeight(),
                contentScale = if (birdDiscovered) contentScale else ContentScale.FillHeight,
                colorFilter = colorFilter
            )
        }
    }
}
