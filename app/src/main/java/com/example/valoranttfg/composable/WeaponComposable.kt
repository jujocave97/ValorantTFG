package com.example.valoranttfg.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.*
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
import com.example.valoranttfg.model.Weapon
import com.example.valoranttfg.service.recopilarWeapons
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullWeaponsScreen(navController: NavController) {
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
                            placeholder = { Text("Buscar arma...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = "Armas",
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
                WeaponListScreen(searchQuery = searchQuery)
            }
        }
    )
}

@Composable
fun WeaponListScreen(searchQuery: String) {
    var weapons by remember { mutableStateOf<List<Weapon>?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as MainActivity).lifecycleScope.launch {
            weapons = recopilarWeapons()
        }
    }

    weapons?.let {
        val filteredWeapons = it.filter { weapon ->
            weapon.displayName.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn {
            items(filteredWeapons) { weapon ->
                WeaponItem(weapon)
            }
        }
    }
}

@Composable
fun WeaponItem(weapon: Weapon) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = weapon.displayName)
        Text(text = weapon.category)
        URLImageWeapon(
            modifier = Modifier.size(width = 500.dp, height = 200.dp),
            url = weapon.displayIcon,
            contentDescription = "Imagen de arma"
        )
    }
}

@Composable
fun URLImageWeapon(
    modifier: Modifier = Modifier,
    url: String,
    colorFilter: ColorFilter? = null,
    birdDiscovered: Boolean = true,
    contentScale: ContentScale = ContentScale.FillWidth,
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
                modifier = modifier.fillMaxSize(),
                contentScale = if (birdDiscovered) contentScale else ContentScale.FillHeight,
                colorFilter = colorFilter
            )
        }
    }
}
