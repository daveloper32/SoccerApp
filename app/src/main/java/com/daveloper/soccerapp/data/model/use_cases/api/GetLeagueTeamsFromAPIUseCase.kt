package com.daveloper.soccerapp.data.model.use_cases.api

import com.daveloper.soccerapp.auxiliar.exception_provider.ExceptionProviderHelper
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.TeamsInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLeagueTeamsFromAPIUseCase @Inject constructor(
    private val teamsAPI: TeamsInfoService,
    private  val exceptionProviderHelper: ExceptionProviderHelper
) {
    suspend fun getData (
        soccerLeague: String
    ) : List<Team> {
        return withContext(Dispatchers.IO) {
            if (soccerLeague.isNotEmpty()){
                try {
                    teamsAPI.searchTeamsInfoByLeague(soccerLeague)
                } catch (e: Exception){
                    throw Exception(e)
                }
            } else {
                throw Exception(exceptionProviderHelper.getApiException(1))
            }
        }
    }
}