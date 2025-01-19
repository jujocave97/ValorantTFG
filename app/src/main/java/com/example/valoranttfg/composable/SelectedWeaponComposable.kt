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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.example.valoranttfg.model.ShopData
import com.example.valoranttfg.model.Skin
import com.example.valoranttfg.model.Weapon
import com.example.valoranttfg.model.WeaponStats
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponSelected(weapon: Weapon, navController: NavController){
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
                    Text(text = weapon.displayName,
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
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente todo el contenido
        ) {
            URLImageWeapon(
                modifier = Modifier.size(width = 500.dp, height = 200.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                url = weapon.displayIcon,
                contentDescription = "Imagen de ${weapon.displayName}"
            )
            Spacer(Modifier.padding(10.dp))
            Text(
                text = "Estadísticas",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 10.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            Stats(weaponStats = weapon.weaponStats)
            Text(
                text = "Datos de la tienda",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 10.dp), // Espacio superior
                textAlign = TextAlign.Center
            )
            ShopData(shopData = weapon?.shopData)
            Spacer(Modifier.padding(10.dp))
            Button(
                onClick = { val weaponJson = Uri.encode(gson.toJson(weapon)) // Codificar JSON
                    navController.navigate("Skins_Collection_Screen/$weaponJson") },
                modifier = Modifier
                    .padding(16.dp) // Espaciado alrededor del botón
            ) {
                Text(text = "Skins")
            }
        }


    }
}

@Composable
fun Stats(weaponStats: WeaponStats?){
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
                text = "Fire rate: "+weaponStats?.fireRate,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text = "Cargador: "+weaponStats?.magazineSize,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text = "Tiempo de recarga: "+weaponStats?.reloadTimeSeconds,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ShopData(shopData: ShopData?){
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
                text = "Coste: "+shopData?.cost,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text = "Categoría: "+shopData?.category,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
