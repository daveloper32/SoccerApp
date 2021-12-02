package com.daveloper.soccerapp.data.local_database.room.dao

import androidx.room.*
import com.daveloper.soccerapp.data.model.entity.Team

@Dao
interface TeamDao {
    @Insert()
    fun insert(team: Team)

    @Query("SELECT * FROM teams WHERE name = :teamName")
    fun getDataFromXTeam(teamName: String): Team

    @Query("SELECT * FROM teams WHERE league = :leagueName")
    fun getTeamsDataFromXLeague(leagueName: String): List<Team>

    @Update
    fun updateATeam(team: Team)

}