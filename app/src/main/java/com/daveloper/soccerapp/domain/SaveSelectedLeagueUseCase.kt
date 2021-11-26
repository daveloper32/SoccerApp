package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class SaveSelectedLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun saveInfo (
        context: Context,
        league: String
    ) {
        repository.saveSelectedLeague(context, league)
    }
}