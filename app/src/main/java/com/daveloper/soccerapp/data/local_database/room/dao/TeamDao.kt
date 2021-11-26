package com.daveloper.soccerapp.data.local_database.room.dao

import androidx.room.*
import com.daveloper.soccerapp.data.model.entity.Team

@Dao
interface TeamDao {
    @Insert()
    fun insert(team: Team)

    @Query("SELECT * FROM teams ORDER BY name ASC")
    fun getAllTeams(): MutableList<Team>

    @Query("SELECT * FROM teams WHERE name = :teamName")
    fun getDataFromXTeam(teamName: String): Team

    @Query("SELECT * FROM teams WHERE league = :leagueName")
    fun getAllTeamsFromXLeague(leagueName: String): MutableList<Team>

    @Update
    fun updateATeam(team: Team)

    @Delete
    fun deleteATeam(team: Team)
}