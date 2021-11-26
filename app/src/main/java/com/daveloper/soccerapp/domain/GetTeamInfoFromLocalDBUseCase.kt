package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.NextTeamEventsDataRepository
import javax.inject.Inject

class GetTeamInfoFromLocalDBUseCase @Inject constructor(
    private val repository: NextTeamEventsDataRepository
) {
    suspend fun getInfo (
        teamName: String,
        context: Context
    ) : Team {
        return repository.getTeamInfo(teamName, context)
    }
}