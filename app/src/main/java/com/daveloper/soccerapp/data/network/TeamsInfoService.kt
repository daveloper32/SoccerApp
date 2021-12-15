package com.daveloper.soccerapp.data.network

import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class TeamsInfoService @Inject constructor(
    private val retrofit: Retrofit
) {

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