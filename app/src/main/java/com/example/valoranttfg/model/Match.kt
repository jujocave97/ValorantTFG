package com.example.valoranttfg.model

data class Match(
    val id: String,
    val teams: List<TeamMatch>,
    val status: String,
    val ago: String,
    val event: String,
    val tournament: String,
    val img: String
)

data class TeamMatch(
    val name: String,
    val score: String,
    val country: String,
    val won: Boolean
)

data class MatchResponse(
    val status: String,
    val size: Int,
    val data: List<Match>
)
