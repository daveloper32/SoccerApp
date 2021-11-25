package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.core.LeagueIdsFromAPI
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.network.EventsInfoService
import javax.inject.Inject

class NextTeamEventsDataRepository @Inject constructor(
    private val eventsInfoService: EventsInfoService
) {
    suspend fun getXNextTeamEventsInfo (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueIdsFromAPI.getSpanishLeagueID(),
        teamName: String
    ): List<Event> {
        return eventsInfoService.getNextXEventsFromTeam(
            numNextEvents,
            idLeague,
            teamName
        )
    }
}