package com.example.valoranttfg.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.valoranttfg.MainActivity
import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.service.recopilarAgentes
import kotlinx.coroutines.launch

    @Composable
    fun agentListScreen(){
        var agents by remember { mutableStateOf<List<Agent>?>(null)}
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            (context as MainActivity).lifecycleScope.launch{
                agents = recopilarAgentes()
            }
        }

        agents?.let {
            val playableAgents = it.filter { agent -> agent.isPlayableCharacter }
            LazyColumn {
                items(playableAgents) { agent ->
                    agentItem(agent)
                }
            }
        }

    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun agentItem(agent: Agent){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = agent.displayName)
            Text(text = agent.description)
            URLImage(modifier = Modifier.size(200.dp),url = agent.displayIcon, contentDescription = "Imagen de Agente")
            Text(text = agent.role.displayName)
        }
    }

@Composable
fun URLImage(
    modifier: Modifier = Modifier,
    url: String,
    colorFilter: ColorFilter? = null,
    birdDiscovered: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop,
    modifierColumn: Modifier = Modifier,
    elevation: Dp = 13.dp,
    contentDescription: String
) {
    val painter =
        rememberAsyncImagePainter(url)
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