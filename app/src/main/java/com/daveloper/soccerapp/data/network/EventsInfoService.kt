package com.daveloper.soccerapp.data.network

import android.annotation.SuppressLint
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.core.RetrofitHelper
import com.daveloper.soccerapp.data.model.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventsInfoService @Inject constructor(

) {

    private val apiTeamEventsInfo: String =
        "https://www.thesportsdb.com/api/v1/json/2/"

    private val retrofit = RetrofitHelper.getRetrofit(apiTeamEventsInfo)

    @SuppressLint("SimpleDateFormat")
    suspend fun getNextXEventsFromTeam (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        teamName: String
    ) : List<Event>
    {
    return withContext(Dispatchers.IO) {
            // Get the date of today
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val todayDate = dateFormat.parse(
                "$year-" +
                        "$month-" +
                        "$day"
            )
            // Array to save all the events of the season
            var eventsFound: MutableList<Event> = ArrayList()
            // Array to save the X next events
            var xNextEventsFound: MutableList<Event> = ArrayList()

            for (round in 1..38) {
                val eventsPerRound : List<Event> = getOneRoundOfEvents(idLeague, round, year)
                if (!eventsPerRound.isNullOrEmpty()) {
                    eventsFound.addAll(eventsPerRound)
                }
            }
            // Sort the date
            eventsFound.sortBy { it.dateEvent }
            if (!eventsFound.isNullOrEmpty()) {
                var nextXEventsFound = 1
                for (event in eventsFound) {
                    if (!event.dateEvent.isNullOrEmpty()) {
                        val eventDate = dateFormat.parse(event.dateEvent.toString())
                        if (todayDate.equals(eventDate) || eventDate.after(todayDate)) {
                            if (event.homeTeam.equals(teamName) || event.awayTeam.equals(teamName)) {
                                if (nextXEventsFound <= numNextEvents) {
                                    xNextEventsFound.add(event)
                                    nextXEventsFound++
                                } else {
                                    break
                                }
                            } else {
                                continue
                            }
                        }
                    }
                }
            }
            xNextEventsFound?: emptyList()
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
