package com.daveloper.soccerapp.domain

import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class GetTeamsInfoByLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun getInfo(
        soccerLeague: String
    ) : List<Team> {
        return repository.getAllSearchTeamsInfoByLeague(soccerLeague)
    }
}