package com.example.valoranttfg.model

import com.google.gson.annotations.SerializedName

data class ApiResponseMap(
    @SerializedName("Status") val status: Int,
    @SerializedName("data") val data: List<Mapv>
)

data class Mapv(
    @SerializedName("uuid") val uuid: String,

    @SerializedName("displayName") val displayName: String,

    @SerializedName("narrativeDescription") val narrativeDescription: String?,

    @SerializedName("tacticalDescription") val tacticalDescription: String?,

    @SerializedName("coordinates") val coordinates: String?,

    @SerializedName("displayIcon") val displayIcon: String?,

    @SerializedName("listViewIcon") val listViewIcon: String,

    @SerializedName("listViewIconTall") val listViewIconTall: String,

    @SerializedName("splash") val splash: String,

    @SerializedName("stylizedBackgroundImage") val stylizedBackgroundImage: String,

    @SerializedName("premierBackgroundImage") val premierBackgroundImage: String,

    @SerializedName("assetPath") val assetPath: String,

    @SerializedName("mapUrl") val mapUrl: String,

    @SerializedName("xMultiplier") val xMultiplier: Double,

    @SerializedName("yMultiplier") val yMultiplier: Double,

    @SerializedName("xScalarToAdd") val xScalarToAdd: Double,

    @SerializedName("yScalarToAdd") val yScalarToAdd: Double,

    @SerializedName("callouts") val callouts: List<Callout>
)


data class Callout(
    @SerializedName("regionName")
    val regionName: String,

    @SerializedName("superRegionName")
    val superRegionName: String,

    @SerializedName("location")
    val location: Location
)

data class Location(
    @SerializedName("x")
    val x: Double,

    @SerializedName("y")
    val y: Double
)