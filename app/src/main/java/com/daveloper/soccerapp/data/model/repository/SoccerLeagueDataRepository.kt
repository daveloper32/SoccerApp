package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.use_cases.*
import javax.inject.Inject

class SoccerLeagueDataRepository @Inject constructor(
    private val API: GetLeagueTeamsFromAPIUseCase,
    private val getTeamsDataFromXLeagueFromDB: GetTeamsDataFromXLeagueFromDBUseCase,
    private val saveAPIDataInDB: SaveAPIDataInDBUseCase,
    private val saveUserLocalData: SaveSelectedLeagueForUserInUserLocalDataUseCase,
    private val getUserLocalData: GetSavedSelectedLeagueForUserInUserLocalDataUseCase
){

    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return API.getData(soccerLeague)
    }

    suspend fun getAllTeamsInfoFromALeagueInLocalDB(
        league: String
    ): List<Team> {
        return getTeamsDataFromXLeagueFromDB.getData(league)
    }

    suspend fun saveInfoAPIinLocalDB (
        teamsToAdd: List<Team>
    ) {
        saveAPIDataInDB.save(teamsToAdd)
    }

    suspend fun saveSelectedLeague (
        league: String
    ) {
        saveUserLocalData.save(league)
    }

    suspend fun getSavedSelectedLeague (
    ) : String {
        return getUserLocalData.getData()
    }
}