package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class GetSavedSelectedLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun getData (
        context: Context
    ) : String {
        return repository.getSavedSelectedLeague(context)
    }
}