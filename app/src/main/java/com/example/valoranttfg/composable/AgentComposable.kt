package com.example.valoranttfg.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.annotation.ExperimentalCoilApi
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
            LazyColumn {
                items(it) { agent ->
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
            Image(
                painter = rememberImagePainter(agent.displayIcon),
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
