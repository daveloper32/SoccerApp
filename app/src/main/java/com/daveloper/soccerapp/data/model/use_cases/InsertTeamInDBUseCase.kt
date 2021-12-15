package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.auxiliar.ext_fun.isNotNull
import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class InsertTeamInDBUseCase @Inject constructor(
    private val teamDao: TeamDao
){
    suspend fun insert (
        team: Team
    ) {
        withContext(Dispatchers.IO){
            if (team.isNotNull()){
                try {
                    teamDao.insert(team)
                } catch (e: Exception){
                    throw Exception(e)
                }
            } else {
                throw Exception("The parameter 'team' from the class Team is invalid (null)")
            }
        }
    }
}