package com.example.valoranttfg.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valoranttfg.room.dao.AgentDao
import com.example.valoranttfg.room.entities.AgentEntity



@Database(entities = [AgentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "valorant_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}