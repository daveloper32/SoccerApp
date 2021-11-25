package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.TeamsInfoService
import javax.inject.Inject

class SoccerLeagueDataRepository @Inject constructor(
    private val teamsInfoAPI: TeamsInfoService

){
    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return teamsInfoAPI.searchTeamsInfoByLeague(soccerLeague)
    }
}