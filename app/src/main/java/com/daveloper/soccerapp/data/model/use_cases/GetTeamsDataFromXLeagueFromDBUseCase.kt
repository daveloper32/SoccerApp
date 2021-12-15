package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetTeamsDataFromXLeagueFromDBUseCase @Inject constructor(
    private val teamDao: TeamDao
) {
    suspend fun getData (
        league: String
    ): List<Team> {
        return withContext(Dispatchers.IO) {
            if (league.isNotEmpty()){
                try {
                    teamDao.getTeamsDataFromXLeague(league)
                } catch (e: Exception){
                    throw Exception(e)
                }
            } else {
                throw Exception("The parameter 'league' is invalid (empty String)")
            }
        }
    }
}