package com.example.valoranttfg.room.dao

import androidx.room.*
import com.example.valoranttfg.room.entities.AgentEntity

@Dao
interface AgentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgent(agent: AgentEntity)

    @Query("SELECT * FROM agents WHERE uuid = :uuid")
    suspend fun getAgent(uuid: String): AgentEntity  // Debe devolver un tipo específico, no Object.

    @Query("SELECT * FROM agents")
    suspend fun getAllAgents(): List<AgentEntity>

    @Query("DELETE FROM agents WHERE uuid = :uuid")
    suspend fun deleteAgent(uuid: String)

}