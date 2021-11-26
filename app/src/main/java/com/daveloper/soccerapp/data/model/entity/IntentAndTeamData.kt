package com.daveloper.soccerapp.data.model.entity

import androidx.appcompat.app.AppCompatActivity

data class IntentAndTeamData (
    var activity: Class<out AppCompatActivity?>,
    var teamSelected: String
)