package com.example.valoranttfg.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valoranttfg.composable.AgentSelected
import com.example.valoranttfg.composable.AgentsDBScreen
import com.example.valoranttfg.composable.FullAgentsScreen
import com.example.valoranttfg.composable.FullMapsScreen
import com.example.valoranttfg.composable.FullWeaponsScreen
import com.example.valoranttfg.composable.HabilidadesAgente
import com.example.valoranttfg.composable.HomeScreen
import com.example.valoranttfg.composable.MatchScreen
import com.example.valoranttfg.composable.RegionTeams
import com.example.valoranttfg.composable.SkinsCollection
import com.example.valoranttfg.composable.TeamsComposable
import com.example.valoranttfg.composable.WeaponSelected
import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.model.Team
import com.example.valoranttfg.model.Weapon
import com.example.valoranttfg.room.dao.AgentViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val gson = Gson()
    val agentViewModel: AgentViewModel = viewModel()

    NavHost(navController= navController, startDestination = "Home_Screen"){
        composable("Home_Screen"){
            HomeScreen(navController)
        }
        composable("Agents_Screen"){
            FullAgentsScreen(navController)
        }
        composable("Weapons_Screen"){
            FullWeaponsScreen(navController)
        }
        composable("Maps_Screen"){
            FullMapsScreen(navController)
        }
        composable("Teams_Screen"){
            TeamsComposable(navController)
        }
        composable("Match_Screen"){
            MatchScreen(navController)
        }
        composable("Agents_DB_Screen"){
            AgentsDBScreen(agentViewModel, navController)
        }
        composable("Agent_Selected_Screen/{agentJson}"){ backStackEntry ->
            val agentJson = backStackEntry.arguments?.getString("agentJson")
            val agent = gson.fromJson(agentJson, Agent::class.java)
            AgentSelected(agent = agent, navController, agentViewModel)
        }
        composable("Skills_Agent_Screen/{agentJson}"){ backStackEntry ->
            val agentJson = backStackEntry.arguments?.getString("agentJson")
            val agent = gson.fromJson(agentJson, Agent::class.java)
            HabilidadesAgente(agent = agent, navController)
        }
        composable("Weapon_Selected_Screen/{weaponJson}"){ backStackEntry ->
            val weaponJson = backStackEntry.arguments?.getString("weaponJson")
            val weapon = gson.fromJson(weaponJson, Weapon::class.java)
            WeaponSelected(weapon = weapon, navController)
        }
        composable("Skins_Collection_Screen/{weaponJson}"){ backStackEntry ->
            val weaponJson = backStackEntry.arguments?.getString("weaponJson")
            val weapon = gson.fromJson(weaponJson, Weapon::class.java)
            SkinsCollection(weapon = weapon, navController)
        }
        composable("Region_Teams_Screen/{region}"){ backStackEntry ->
            val region = backStackEntry.arguments?.getString("region") ?: ""
            RegionTeams(navController,region = region)
        }

    }
}