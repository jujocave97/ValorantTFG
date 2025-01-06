package com.example.valoranttfg.composable

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valoranttfg.model.Skin
import com.example.valoranttfg.model.Weapon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkinsCollection(weapon: Weapon, navController: NavController){
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
                        text = weapon.displayName,
                        style = MaterialTheme.typography.titleLarge
                    ) // Centrado del título
                }
            }
        )
        weapon.skins?.let {
            val skin = it

            LazyColumn {
                items(skin) { map ->
                    Skin(map)
                }
            }
        }
    }
}

@Composable
fun Skin(skin: Skin){
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
                text = skin.displayName,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            URLImageWeapon(
                modifier = Modifier.size(width = 500.dp, height = 200.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                url = skin.displayIcon,
                contentDescription = "Imagen de ${skin.displayName}"
            )
        }
    }
}
