package com.daveloper.soccerapp.data.network

import com.daveloper.soccerapp.data.model.entity.EventsInfo
import com.daveloper.soccerapp.data.model.entity.TeamsInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getTeamsBySoccerLeague (
        @Url url: String
    ) : Response<TeamsInfo>

    @GET
    suspend fun getEventsFromTeam (
        @Url url: String
    ) : Response<EventsInfo>
}


// All API's Page https://www.thesportsdb.com/api.php

/* All leagues API
    https://www.thesportsdb.com/api/v1/json/2/all_leagues.php
    Important:
    -> ID's from every league.
    -> League's name
    -> Sport
*/

/* ID's and Names of some Leagues on the API

    1. Spanish La Liga - ID = 4335
    2. English Premier League - ID = 4328
    3. Italian Serie A - ID = 4332

 */

/* All teams information from a League

    https://www.thesportsdb.com/api/v1/json/2/search_all_teams.php?l=Name%20Of%20The%20League
    OR
    https://www.thesportsdb.com/api/v1/json/2/search_all_teams.php?l=Name_Of_The_League

    Important:
    -> Team name.
    -> Stadium name
    -> Team Description
    -> Foundation Year
    -> Team Badge url photo
    -> Team Jersey url phot
    -> Website and Social Networks URL's
        (facebook, twitter, instagram, youtube)
 */

/* Events in a specific round by league id/round/season

    https://www.thesportsdb.com/api/v1/json/2/eventsround.php?id=0000&r=00&s=0000-0000

    ID value: from the league
    Round value: 1..38
    Season value: start season year - end season year -> like right now 2021-2022

    Important:
    -> Event name (x team vs x team).
    -> Home Team name
    -> Away Team name
    -> Date Event
    -> Some others, not too much relevant (round value, league name, id event)

 */