package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetDataFromXTeamFromDBUseCase @Inject constructor(
    private val teamDao: TeamDao
) {
    suspend fun getData (
        teamName: String
    ): Team? {
        return withContext(Dispatchers.IO) {
            if (teamName.isNotEmpty()) {
                try {
                    teamDao.getDataFromXTeam(teamName)
                } catch (e: Exception) {
                    throw Exception(e)
                }
            } else {
                throw Exception("The parameter 'teamName' is invalid (empty String)")
            }
        }
    }
}