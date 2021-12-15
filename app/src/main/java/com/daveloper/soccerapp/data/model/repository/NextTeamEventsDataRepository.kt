package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.use_cases.GetDataFromXTeamFromDBUseCase
import com.daveloper.soccerapp.data.model.use_cases.GetNextXTeamEventsFromAPIUseCase
import javax.inject.Inject

class NextTeamEventsDataRepository @Inject constructor(
    private val getNextXTeamEvents: GetNextXTeamEventsFromAPIUseCase,
    private val getDataFromXTeamFromDBUseCase: GetDataFromXTeamFromDBUseCase
) {

    suspend fun getXNextTeamEventsInfo (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        teamName: String
    ): List<Event> {
        return getNextXTeamEvents.getData(
            numNextEvents,
            idLeague,
            teamName
        )
    }

    suspend fun getTeamInfo (
        teamName: String
    ) : Team? {
        return getDataFromXTeamFromDBUseCase.getData(teamName)
    }

    suspend fun getBadgeImageTeam (
        teamName: String
    ) : String? {
        return getDataFromXTeamFromDBUseCase.getData(teamName)?.teamBadge
    }
}