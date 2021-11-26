package com.daveloper.soccerapp.data.model.repository

import android.content.Context
import com.daveloper.soccerapp.data.local_database.dao.TeamDao
import com.daveloper.soccerapp.data.local_database.database.RoomTeamsDatabase
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.TeamsInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SoccerLeagueDataRepository @Inject constructor(
    private val teamsInfoAPI: TeamsInfoService
){
    private lateinit var dB: RoomTeamsDatabase
    private lateinit var teamDao: TeamDao

    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return teamsInfoAPI.searchTeamsInfoByLeague(soccerLeague)
    }

    suspend fun saveInfoAPIinLocalDB (
        teamsToAdd: List<Team>,
        context: Context
    ) {
        withContext(Dispatchers.IO) {
            dB = RoomTeamsDatabase.getDatabase(context)
            teamDao = dB.teamDao()
            if (!teamsToAdd.isNullOrEmpty()) {
                for (team in teamsToAdd) {
                    if (team.name?.let { teamDao.getDataFromXTeam(it) } != null) {
                        // If its not null the Team exist -> Needs an Update
                        teamDao.updateATeam(team)
                    } else {
                        // If its null the Team doesnt exist -> Needs an Insert
                        teamDao.insert(team)
                    }
                }
            }
        }
    }
}