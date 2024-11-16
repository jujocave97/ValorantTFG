package com.example.valoranttfg.service

import com.example.valoranttfg.model.Agent
import com.example.valoranttfg.model.ApiResponseAgent
import com.example.valoranttfg.model.ApiResponseMap
import com.example.valoranttfg.model.Mapv
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun recopilarMapas(): List<Mapv>? {
    var client = OkHttpClient()
    var request = Request.Builder().url("https://valorant-api.com/v1/maps?language=es-ES").build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val json = response.body?.string() ?: return@withContext null
        val apiResponse = Gson().fromJson(json, ApiResponseMap::class.java)
        apiResponse.data
    }
}