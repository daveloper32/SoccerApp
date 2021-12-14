package com.daveloper.soccerapp.domain

import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import javax.inject.Inject

class GetSavedSelectedLeagueUseCase @Inject constructor(
    private val repository: SoccerLeagueDataRepository
) {
    suspend fun getData (
    ) : String {
        return repository.getSavedSelectedLeague()
    }
}