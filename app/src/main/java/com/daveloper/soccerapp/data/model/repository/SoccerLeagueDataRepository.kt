package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.APIService
import com.daveloper.soccerapp.data.network.TeamsInfoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject


class SoccerLeagueDataRepository @Inject constructor(
    private val teamsInfoAPI: TeamsInfoService

){

    private val idSpanishLaLigaFromAPI: Int = 4335
    private val idEnglishPremierLeagueFromAPI: Int = 4328
    private val idItalianSerieAFromAPI: Int = 4332

    private val apiTeamsInfo: String =
        "https://www.thesportsdb.com/api/v1/json/2/"
    private val apiTeamEventsInfo: String =
        "https://www.thesportsdb.com/api/v1/json/2/eventsround.php?id="

    suspend fun getAllSearchTeamsInfoByLeague(
        soccerLeague: String
    ): List<Team> {
        return teamsInfoAPI.searchTeamsInfoByLeague(soccerLeague)
    }

    fun getRetrofit(apiToUse: String) : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(apiToUse)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    fun getNextXEventsFromTeam (
        numNextEvents: Int = 5,
        idLeague: Int = idSpanishLaLigaFromAPI,
        teamName: String
    )
    {
        // Get the date of today
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val date = calendar.get(Calendar.DATE)
        // Array to save all the events of the season
        var eventsFound: MutableList<Event> = ArrayList()
        // Array to save the X next events
        var xNextEventsFound: MutableList<Event> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            for (round in 1..38) {
                val search =
                    getRetrofit(apiTeamEventsInfo)
                        .create(APIService::class.java)
                        .getTeamsBySoccerLeague("$idLeague&r=$round&s=$year-${year+1}")
                val eventsInfo = search.body()
                if (search.isSuccessful) {
                    // Add to eventsFound
                        eventsFound.plus(eventsInfo?.teams)
                }
            }
            if (!eventsFound.isNullOrEmpty()) {
                var nextXEventsFound = 0
                for (event in eventsFound) {
                    if (event.dateEvent != null && event.dateEvent!! >= date.toString()) {
                        if (nextXEventsFound <= numNextEvents) {
                            xNextEventsFound.add(event)
                            nextXEventsFound++
                        } else {
                            break
                        }
                    }
                }
            }
        }


    }

}