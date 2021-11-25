package com.daveloper.soccerapp.domain


import com.daveloper.soccerapp.core.LeagueIdsFromAPI
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.repository.NextTeamEventsDataRepository
import javax.inject.Inject

class GetXNextTeamEventsInfoUseCase @Inject constructor(
    private val repository: NextTeamEventsDataRepository
) {
    suspend fun getInfo (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueIdsFromAPI.getSpanishLeagueID(),
        teamName: String
    ) : List<Event> {
        return repository.getXNextTeamEventsInfo(
            numNextEvents,
            idLeague,
            teamName
        )
    }
}