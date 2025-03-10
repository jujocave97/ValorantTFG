package com.example.valoranttfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.valoranttfg.composable.AgentListScreen
import com.example.valoranttfg.composable.FullMapsScreen
import com.example.valoranttfg.composable.HomeScreen
import com.example.valoranttfg.composable.WeaponListScreen
import com.example.valoranttfg.navigation.AppNavigation
import com.example.valoranttfg.ui.theme.ValorantTFGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValorantTFGTheme {
                AppNavigation()
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ValorantTFGTheme {
        Greeting("Android")
    }
}