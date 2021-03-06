package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class GetTeamsInfoByLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun getInfo(
        soccerLeague: String,
        context: Context
    ) : List<Team> {
        val data = repository.getAllSearchTeamsInfoByLeague(soccerLeague)
        repository.saveInfoAPIinLocalDB(data, context)
        return data
    }
}