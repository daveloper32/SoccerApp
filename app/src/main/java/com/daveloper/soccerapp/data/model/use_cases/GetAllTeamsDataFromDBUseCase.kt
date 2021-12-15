package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllTeamsDataFromDBUseCase @Inject constructor(
    private val teamDao: TeamDao
) {
    suspend fun getData (): List<Team> {
        return withContext(Dispatchers.IO){
            try {
                teamDao.getAllTeams()
            } catch (e: Exception){
                throw Exception(e)
            }
        }
    }
}