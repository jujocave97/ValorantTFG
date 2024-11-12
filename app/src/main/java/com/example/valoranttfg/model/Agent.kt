package com.example.valoranttfg.model
import com.google.gson.annotations.SerializedName

data class ApiResponseAgent(
    @SerializedName("Status") val status: Int,
    @SerializedName("data") val data: List<Agent>
)

data class Agent(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("fullPortrait") val fullPortrait: String,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("role") val role: Role
)

data class Ability(
    @SerializedName("slot") val slot: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String,
)

data class Role(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("description") val description: String,
    @SerializedName("displayIcon") val displayIcon: String,
)

