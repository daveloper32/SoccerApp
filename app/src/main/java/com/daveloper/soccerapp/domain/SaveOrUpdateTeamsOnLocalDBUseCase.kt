package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class SaveOrUpdateTeamsOnLocalDBUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun saveOrUpdateOnDB (
        teamsToAdd: List<Team>,
        context: Context
    ) {
        repository.saveInfoAPIinLocalDB(teamsToAdd, context)
    }
}