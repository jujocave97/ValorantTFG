package com.example.valoranttfg.room.dao

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.valoranttfg.room.AppDatabase
import com.example.valoranttfg.room.entities.AgentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentViewModel(application: Application) : AndroidViewModel(application) {

    private val agentDao: AgentDao = AppDatabase.getDatabase(application).agentDao()

    // Función para obtener todos los agentes
    fun getAllAgents(): LiveData<List<AgentEntity>> = agentDao.getAllAgents()


    // Función para insertar un nuevo agente
    fun insertAgent(agent: AgentEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            agentDao.insertAgent(agent) // Insertamos el agente de manera asíncrona
        }
    }


    fun deleteAgent(uuid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            agentDao.deleteAgent(uuid)  // Eliminamos el agente con el uuid
        }
    }

    fun isAgentInFavorites(uuid: String): LiveData<Boolean> {
        return liveData(Dispatchers.IO) {
            val agent = agentDao.getAgent(uuid)
            emit(agent != null) // Si el agente existe, emite 'true', si no, 'false'
        }
    }
}
