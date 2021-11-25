package com.daveloper.soccerapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class EventsInfo (
    @SerializedName("events") var events: List<Event>
)