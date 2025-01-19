package com.example.valoranttfg.model

import com.google.gson.annotations.SerializedName

data class ApiResponseTeams(
    @SerializedName("status") val status: String,
    @SerializedName("region") val region: String,
    @SerializedName("size") val size: Int,
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("data") val data: List<Team>
)

data class Pagination(
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("totalElements") val totalElements: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("hasNextPage") val hasNextPage: Boolean
)

data class Team(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("country") val country: String
)
