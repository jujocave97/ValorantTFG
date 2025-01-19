package com.example.valoranttfg.service

import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.model.ApiResponseAgent
import com.example.valoranttfg.model.ApiResponseTeams
import com.example.valoranttfg.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun recopilarEquipos(region: String): List<Team>? {
    val client = OkHttpClient()
    val url = "https://vlr.orlandomm.net/api/v1/teams?region=$region" // Incluye la región como parámetro en la URL
    val request = Request.Builder().url(url).build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val json = response.body?.string() ?: return@withContext null
        val apiResponse = Gson().fromJson(json, ApiResponseTeams::class.java)
        apiResponse.data
    }
}
