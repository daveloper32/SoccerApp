package com.daveloper.soccerapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class TeamsInfo (
    @SerializedName("teams") var teams: List<Team>
)