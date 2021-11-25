package com.daveloper.soccerapp.data.network

import com.daveloper.soccerapp.core.RetrofitHelper
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsInfoService @Inject constructor() {
    private val apiTeamsInfo: String =
        "https://www.thesportsdb.com/api/v1/json/2/"
    private val retrofit = RetrofitHelper.getRetrofit(apiTeamsInfo)

    suspend fun searchTeamsInfoByLeague (
        soccerLeague: String
    ) : List<Team> {
        return withContext(Dispatchers.IO) {
            val search =
                retrofit
                    .create(APIService::class.java)
                    .getTeamsBySoccerLeague("search_all_teams.php?l=$soccerLeague")
            val teamsInfo = search.body()
            teamsInfo?.teams ?: emptyList()
        }
    }
}