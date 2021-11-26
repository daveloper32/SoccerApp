package com.daveloper.soccerapp.core

object LeagueAPIHelper {
    private val spanishLeagueN = "Spanish La Liga"
    private val englishLeagueN = "English Premier League"
    private val italianLeagueN = "Italian Serie A"
    private val spanishLeague = "Spanish_La_Liga"
    private val englishLeague = "English_Premier_League"
    private val italianLeague = "Italian_Serie_A"
    private val idSpanishLaLigaFromAPI: Int = 4335
    private val idEnglishPremierLeagueFromAPI: Int = 4328
    private val idItalianSerieAFromAPI: Int = 4332
    fun getSpanishLeagueID () = idSpanishLaLigaFromAPI
    fun getEmglishLeagueID () = idEnglishPremierLeagueFromAPI
    fun getItalianLeagueID () = idItalianSerieAFromAPI
    fun getSpanishLeagueN () = spanishLeagueN
    fun getEnglishLeagueN () = englishLeagueN
    fun getItalianLeagueN () = italianLeagueN
    fun getSpanishLeague () = spanishLeague
    fun getEnglishLeague () = englishLeague
    fun getItalianLeague () = italianLeague
    fun getAllLeaguesN() = listOf<String>(
        spanishLeagueN,
        englishLeagueN,
        italianLeagueN
    )
    fun getAllLeagues() = listOf<String>(
        spanishLeague,
        englishLeague,
        italianLeague
    )
}