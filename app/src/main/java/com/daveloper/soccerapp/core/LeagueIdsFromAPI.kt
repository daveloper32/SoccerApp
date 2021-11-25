package com.daveloper.soccerapp.core

object LeagueIdsFromAPI {
    private val idSpanishLaLigaFromAPI: Int = 4335
    private val idEnglishPremierLeagueFromAPI: Int = 4328
    private val idItalianSerieAFromAPI: Int = 4332
    fun getSpanishLeagueID () = idSpanishLaLigaFromAPI
    fun getEmglishLeagueID () = idEnglishPremierLeagueFromAPI
    fun getItalianLeagueID () = idItalianSerieAFromAPI
}