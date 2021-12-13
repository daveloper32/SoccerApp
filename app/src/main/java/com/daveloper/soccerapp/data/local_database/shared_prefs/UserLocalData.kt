package com.daveloper.soccerapp.data.local_database.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import com.daveloper.soccerapp.core.LeagueAPIHelper
import javax.inject.Inject

class UserLocalData @Inject constructor(
    private val context: Context
) {
    companion object {
        private lateinit var localData: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        private const val userLocalData: String = "datos_locales_usuario"
        private const val selected_league_name_key: String = "selected_league_name"
        private const val empty: String = ""
    }

    fun setUserLocalData (
        selectedLeague: String
    ) {
        if (selectedLeague.isNotEmpty()) {
            localData = context.getSharedPreferences(
                userLocalData,
                Context.MODE_PRIVATE
            )
            editor = localData.edit()
            editor.putString(
                selected_league_name_key,
                selectedLeague
            )
            editor.commit()
        }
    }

    fun getUserLocalData (
    ) : String {
        localData = context.getSharedPreferences(
            userLocalData,
            Context.MODE_PRIVATE
        )
        if (!localData.getString(selected_league_name_key, empty).isNullOrEmpty()) {
            return localData.getString(selected_league_name_key, empty)!!
        } else {
            return LeagueAPIHelper.getSpanishLeagueN()
        }
    }
}