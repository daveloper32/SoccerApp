package com.daveloper.soccerapp.data.model.repository

import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.network.EventsInfoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NextTeamEventsDataRepository @Inject constructor(
    private val eventsInfoService: EventsInfoService,
    private val teamDao: TeamDao
) {
    //private lateinit var dB: RoomTeamsDatabase
    //private lateinit var teamDao: TeamDao

    suspend fun getXNextTeamEventsInfo (
        numNextEvents: Int = 5,
        idLeague: Int = LeagueAPIHelper.getSpanishLeagueID(),
        teamName: String
    ): List<Event> {
        return eventsInfoService.getNextXEventsFromTeam(
            numNextEvents,
            idLeague,
            teamName
        )
    }

    suspend fun getTeamInfo (
        teamName: String
    ) : Team {
        return withContext(Dispatchers.IO) {
            //dB = RoomTeamsDatabase.getDatabase(context)
            //teamDao = dB.teamDao()
            teamDao.getDataFromXTeam(teamName)
        }
    }

    suspend fun getBadgeImageTeam (
        teamName: String
    ) : String? {
        return withContext(Dispatchers.IO) {
            //dB = RoomTeamsDatabase.getDatabase(context)
            //teamDao = dB.teamDao()
            teamDao.getDataFromXTeam(teamName).teamBadge
        }
    }
}