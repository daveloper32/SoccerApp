package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class SaveSelectedLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun saveInfo (
        league: String
    ) {
        repository.saveSelectedLeague(league)
    }
}