package com.daveloper.soccerapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class Event (
    @SerializedName("strHomeTeam") var homeTeam: String?,
    @SerializedName("strAwayTeam") var awayTeam: String?,
    @SerializedName("dateEvent") var dateEvent: String?,
    @SerializedName("strTime") var timeEvent: String?
)