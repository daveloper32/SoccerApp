package com.daveloper.soccerapp.data.model.use_cases.room_db

import com.daveloper.soccerapp.auxiliar.exception_provider.ExceptionProviderHelper
import com.daveloper.soccerapp.data.model.entity.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SaveAPIDataInDBUseCase @Inject constructor(
    private val getAllTeamsDataFromDBUseCase: GetAllTeamsDataFromDBUseCase,
    private val getDataFromXTeamFromDBUseCase: GetDataFromXTeamFromDBUseCase,
    private val insertTeamInDBUseCase: InsertTeamInDBUseCase,
    private val updateTeamInDBUseCase: UpdateTeamInDBUseCase,
    private val exceptionProviderHelper: ExceptionProviderHelper
) {

    suspend fun save (
        teams: List<Team>
    ) {
        withContext(Dispatchers.IO) {
            if (!teams.isNullOrEmpty()) {
                try {
                    for (team in teams) {
                        if (!getAllTeamsDataFromDBUseCase.getData().isNullOrEmpty()) {
                            if (team.name?.let {
                                    getDataFromXTeamFromDBUseCase.getData(it)
                            } != null) {
                                val teamFounded = getDataFromXTeamFromDBUseCase.getData(team.name!!)
                                if (teamFounded!!.name == team.name){
                                    // If its not null the Team exist -> Needs an Update
                                    updateTeamInDBUseCase.update(team)
                                }
                            } else {
                                // If its null the Team doesnt exist -> Needs an Insert
                                insertTeamInDBUseCase.insert(team)
                            }

                        } else {
                            // If the DB is null or empty the Team doesnt exit -> Needs an Insert
                            insertTeamInDBUseCase.insert(team)
                        }
                    }
                } catch (e: Exception) {
                    throw Exception(e)
                }
            } else {
                throw Exception(exceptionProviderHelper.getLocalDBException(4))
            }
        }
    }
}