package com.daveloper.soccerapp.domain

import android.content.Context
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.repository.NextTeamEventsDataRepository
import javax.inject.Inject

class GetXNextTeamEventsInfoUseCase @Inject constructor(
    private val repository: NextTeamEventsDataRepository
) {
    suspend fun getInfo (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        teamName: String,
        context: Context,
        internetConnection: Boolean
    ) : List<Event>? {
        if (internetConnection) {
            val data =  repository.getXNextTeamEventsInfo(
                numNextEvents,
                idLeague,
                teamName
            )
            for (event in data) {
                event.homeTeamBadge = event.homeTeam?.let { repository.getBadgeImageTeam(it, context) }
                event.awayTeamBadge = event.awayTeam?.let { repository.getBadgeImageTeam(it, context) }
            }
            return data
        } else {
            return null
        }
    }
}