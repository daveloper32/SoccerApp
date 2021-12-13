package com.daveloper.soccerapp.data.model.repository

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.local_database.room.database.RoomTeamsDatabase
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
    //private lateinit var dB: RoomTeamsDatabase
    //private lateinit var teamDao: TeamDao

    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return teamsInfoAPI.searchTeamsInfoByLeague(soccerLeague)
    }

    suspend fun getAllTeamsInfoFromALeagueInLocalDB(
        league: String
    ): List<Team> {
        return  withContext(Dispatchers.IO) {
            //dB = RoomTeamsDatabase.getDatabase(context)
            //teamDao = dB.teamDao()
            teamDao.getTeamsDataFromXLeague(league)
        }
    }

    suspend fun saveInfoAPIinLocalDB (
        teamsToAdd: List<Team>
    ) {
        withContext(Dispatchers.IO) {
            //dB = RoomTeamsDatabase.getDatabase(context)
            //teamDao = dB.teamDao()
            if (!teamsToAdd.isNullOrEmpty()) {
                for (team in teamsToAdd) {
                    if (team.name.let { teamDao.getDataFromXTeam(it) } != null) {
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