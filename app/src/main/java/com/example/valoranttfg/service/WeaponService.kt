package com.example.valoranttfg.service


import com.example.valoranttfg.model.ApiResponseWeapon
import com.example.valoranttfg.model.Weapon
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun recopilarWeapons(): List<Weapon>? {
    var client = OkHttpClient()
    var request = Request.Builder().url("https://valorant-api.com/v1/weapons?language=es-ES").build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        val json = response.body?.string() ?: return@withContext null
        val apiResponse = Gson().fromJson(json, ApiResponseWeapon::class.java)
        apiResponse.data
    }
}