package com.daveloper.soccerapp.auxiliar.ext_fun

import com.daveloper.soccerapp.data.model.entity.Team

fun Team.isNull(): Boolean = this == null

fun Team.isNotNull(): Boolean = this != null