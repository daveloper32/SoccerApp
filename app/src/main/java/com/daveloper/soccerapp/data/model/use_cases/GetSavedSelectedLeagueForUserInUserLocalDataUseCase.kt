package com.daveloper.soccerapp.data.model.use_cases

import com.daveloper.soccerapp.data.local_database.shared_prefs.UserLocalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetSavedSelectedLeagueForUserInUserLocalDataUseCase @Inject constructor(
    private val userLocalData: UserLocalData
) {
    suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            try {
                userLocalData.getUserLocalData()
            } catch (e: Exception) {
                throw Exception (e)
            }
        }
    }
}