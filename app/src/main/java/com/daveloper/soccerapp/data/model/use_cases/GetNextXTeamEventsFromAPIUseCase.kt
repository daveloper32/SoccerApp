package com.daveloper.soccerapp.data.model.use_cases

import android.annotation.SuppressLint
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.network.EventsInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.Exception

@SuppressLint("SimpleDateFormat")
class GetNextXTeamEventsFromAPIUseCase @Inject constructor(
    private val eventsInfoService: EventsInfoService
) {

    // Get the date of today
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH) + 1
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val todayDate =
        dateFormat.parse(
            "$year-" +
                    "$month-" +
                    "$day"
        )

    suspend fun getData(
        numNextEvents: Int = 5,
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        teamName: String
    ): List<Event>
    {
        return withContext(Dispatchers.IO) {
            try {
                // Get events found from API
                var eventsFound: MutableList<Event> = eventsInfoService.getAllEventsInALeague(idLeague, year)
                // Verify the List is not empty or null
                if (!eventsFound.isNullOrEmpty()){
                    // Sort the mutable list by date of the Event
                    eventsFound.sortBy { it.dateEvent }
                    // Get the next X events
                    val xNextEventsFound: MutableList<Event> =
                        findNextXEvents(
                            numNextEvents,
                            eventsFound,
                            teamName
                        )
                    // return the next events
                    xNextEventsFound?: emptyList()
                } else {
                    throw Exception("The API could find any Event -> empty or null MutableList<Event>")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    private fun findNextXEvents (
        numNextEvents: Int = 5,
        eventsFound: MutableList<Event>,
        teamName: String
    ): MutableList<Event> {
        // Array to save the X next events
        var nextEventsFound: MutableList<Event> = ArrayList()
        var numNextXEventsFound = 1
        for (event in eventsFound) {
            if (!event.dateEvent.isNullOrEmpty()) {
                val eventDate = dateFormat.parse(event.dateEvent.toString())
                if (todayDate.equals(eventDate) || eventDate.after(todayDate)) {
                    if (event.homeTeam.equals(teamName) || event.awayTeam.equals(teamName)) {
                        if (numNextXEventsFound <= numNextEvents) {
                            nextEventsFound.add(event)
                            numNextXEventsFound++
                        } else {
                            break
                        }
                    } else {
                        continue
                    }
                }
            }
        }
        if (!nextEventsFound.isNullOrEmpty()) {
            return nextEventsFound
        } else {
            throw Exception("No $numNextEvents Next Events found -> emptyArray")
        }
    }
}