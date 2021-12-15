package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.data.local_database.shared_prefs.UserLocalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class SaveSelectedLeagueForUserInUserLocalDataUseCase @Inject constructor(
    private val userLocalData: UserLocalData
) {
    suspend fun save (
        league: String
    ) {
        withContext(Dispatchers.IO) {
            if (league.isNotEmpty()) {
                try {
                    userLocalData.setUserLocalData(league)
                } catch (e: Exception) {
                    throw Exception (e)
                }

            } else {
                throw Exception ("The parameter 'league' is invalid (empty String)")
            }
        }
    }
}