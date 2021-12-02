package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class GetTeamsInfoByLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun getInfo(
        soccerLeague: String,
        context: Context,
        internetConnection: Boolean
    ) : List<Team>? {
        if (internetConnection) {
            var data = repository.getAllSearchTeamsInfoByLeague(soccerLeague)
            if (!data.isNullOrEmpty()) {
                repository.saveInfoAPIinLocalDB(data, context)
                return data
            } else {
                data = repository.getAllTeamsInfoFromALeagueInLocalDB(soccerLeague, context)
                if (!data.isNullOrEmpty()) {
                    return data
                } else {
                    return null
                }
            }
        } else {
            val data = repository.getAllTeamsInfoFromALeagueInLocalDB(
                getNotUnderScoreLeagueName(soccerLeague),
                context
            )
            if (!data.isNullOrEmpty()) {
                return data
            } else {
                return null
            }
        }
    }

    private fun getNotUnderScoreLeagueName (
        underscoreLeagueName: String
    ) : String {
        return when (underscoreLeagueName) {
            LeagueAPIHelper.getSpanishLeague() -> LeagueAPIHelper.getSpanishLeagueN()
            LeagueAPIHelper.getEnglishLeague() -> LeagueAPIHelper.getEnglishLeagueN()
            LeagueAPIHelper.getItalianLeague() -> LeagueAPIHelper.getItalianLeagueN()
            else -> LeagueAPIHelper.getSpanishLeagueN()
        }
    }
}