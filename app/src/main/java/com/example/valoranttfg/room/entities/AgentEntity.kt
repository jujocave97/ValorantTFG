package com.example.valoranttfg.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agents")
data class AgentEntity(
    @PrimaryKey val uuid: String,
    val displayName: String,
    val description: String,
    val fullPortrait: String,
    val displayIcon: String,
    val roleUuid: String // Relación con RoleEntity
)