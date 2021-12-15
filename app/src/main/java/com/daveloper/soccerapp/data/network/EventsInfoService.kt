package com.daveloper.soccerapp.data.network

import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class EventsInfoService @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getAllEventsInALeague (
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        year: Int
    ): MutableList<Event> {
        return withContext(Dispatchers.IO) {
            // Array to save all the events of the season
            var eventsFound: MutableList<Event> = ArrayList()

            for (round in 1..38) {
                val eventsPerRound : List<Event> = getOneRoundOfEvents(idLeague, round, year)
                if (!eventsPerRound.isNullOrEmpty()) {
                    eventsFound.addAll(eventsPerRound)
                }
            }
            eventsFound
        }
    }

    private suspend fun getOneRoundOfEvents (
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        round: Int,
        year: Int
    ) : List<Event> {
        return withContext(Dispatchers.IO) {
            val search =
                retrofit
                    .create(APIService::class.java)
                    .getEventsFromTeam("eventsround.php?id=$idLeague&r=$round&s=$year-${year+1}")
            val eventsInfo = search.body()
            eventsInfo?.events?: emptyList()
        }
    }
}
