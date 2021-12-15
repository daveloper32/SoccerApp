package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.local_database.shared_prefs.UserLocalData
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.TeamsInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SoccerLeagueDataRepository @Inject constructor(
    private val teamsInfoAPI: TeamsInfoService,
    private val teamDao: TeamDao,
    private val userLocalData: UserLocalData
){

    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return teamsInfoAPI.searchTeamsInfoByLeague(soccerLeague)
    }

    suspend fun getAllTeamsInfoFromALeagueInLocalDB(
        league: String
    ): List<Team> {
        return  withContext(Dispatchers.IO) {
            teamDao.getTeamsDataFromXLeague(league)
        }
    }

    suspend fun saveInfoAPIinLocalDB (
        teamsToAdd: List<Team>
    ) {
        withContext(Dispatchers.IO) {
            if (!teamsToAdd.isNullOrEmpty()) {
                for (team in teamsToAdd) {
                    if (!teamDao.getAllTeams().isNullOrEmpty()) {
                        if (team.name?.let { teamDao.getDataFromXTeam(it) } != null) {
                            val teamFounded = teamDao.getDataFromXTeam(team.name!!)
                            if (teamFounded!!.name == team.name){
                                // If its not null the Team exist -> Needs an Update
                                teamDao.updateATeam(team)
                            }
                        } else {
                            // If its null the Team doesnt exist -> Needs an Insert
                            teamDao.insert(team)
                        }
                    } else {
                        // If its null the Team doesnt exist -> Needs an Insert
                        teamDao.insert(team)
                    }
                }
            }
        }
    }

    suspend fun saveSelectedLeague (
        league: String
    ) {
        withContext(Dispatchers.IO){
            userLocalData.setUserLocalData(league)
        }
    }

    suspend fun getSavedSelectedLeague (
    ) : String {
        return withContext(Dispatchers.IO){
            userLocalData.getUserLocalData()
        }
    }
}