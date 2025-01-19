package com.example.valoranttfg.service

import com.example.valoranttfg.model.ApiResponseMap
import com.example.valoranttfg.model.Mapv
import com.example.valoranttfg.model.Match
import com.example.valoranttfg.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun recopilarMatchs(): List<Match>? {
    var client = OkHttpClient()
    var request = Request.Builder().url("https://vlr.orlandomm.net/api/v1/results").build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val json = response.body?.string() ?: return@withContext null
        val apiResponse = Gson().fromJson(json, MatchResponse::class.java)
        apiResponse.data
    }
}