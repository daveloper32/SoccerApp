package com.daveloper.soccerapp.auxiliar.exception_provider

import javax.inject.Inject

class ExceptionProviderHelper @Inject constructor(

){
    // Exceptions
    // Unknown error
    private val unknownException: String = "Unknown error"
    // API exceptions
    private val apiException1: String = "The parameter 'soccerLeague' is invalid (empty String)"
    private val apiException2: String = "The API could find any Event -> empty or null MutableList<Event>"
    private val apiException3: String = "Next Events not found -> emptyArray"
    // Room / Local Database
    private val dBException1: String = "The parameter 'teamName' is invalid (empty String)"
    private val dBException2: String = "The parameter 'league' is invalid (empty String)"
    private val dBException3: String = "The parameter 'team' from the class Team is invalid (null)"
    private val dBException4: String = "The parameter 'teams' is invalid (null or empty List<Team>)"
    private val dBException5: String = "The parameter 'team' from the class Team is invalid (null)"
    // SharedPreferences
    private val userLDException1: String = "The parameter 'league' is invalid (empty String)"
    // MSGS to Exceptions
    // API exceptions
    private val apiMsgException1: String = "Something wrong happen, try again!"
    private val apiMsgException2: String = "Next events not found, try again!"
    private val apiMsgException3: String = "Next events not found, try again!"
    // Room / Local Database
    private val dBMsgException1: String = "Something wrong happen, try again!"
    private val dBMsgException2: String = "Something wrong happen, try again!"
    private val dBMsgException3: String = "Something wrong happen, try again!"
    private val dBMsgException4: String = "Teams not found, try again!"
    private val dBMsgException5: String = "Something wrong happen, try again!"
    // SharedPreferences
    private val userLDMsgException1: String = "Local data couldn't be saved!"
    // Intent
    private val intentMsgException1: String = "The connection to the webpage failed, try again!"
    private val intentMsgException2: String = "The connection to the Facebook webpage failed, try again!"
    private val intentMsgException3: String = "The connection to the Instagram failed, try again!"
    private val intentMsgException4: String = "The connection to the Twitter webpage failed, try again!"
    private val intentMsgException5: String = "The connection to the Youtube webpage failed, try again!"

    fun getApiException(
        numException: Int
    ): String {
        return when (numException) {
            1 -> apiException1
            2 -> apiException2
            3 -> apiException3
            else -> unknownException
        }
    }

    fun getLocalDBException(
        numException: Int
    ): String {
        return when (numException) {
            1 -> dBException1
            2 -> dBException2
            3 -> dBException3
            4 -> dBException4
            5 -> dBException5
            else -> unknownException
        }
    }


    fun getUserLocalDataException(
        numException: Int
    ): String {
        return when (numException) {
            1 -> userLDException1
            else -> unknownException
        }
    }

    fun fromStrExceptionToUserMsg(
        strException: String
    ): String {
        return when (strException) {
            apiException1 -> apiMsgException1
            apiException2 -> apiMsgException2
            apiException3 -> apiMsgException3
            /////
            dBException1 -> dBMsgException1
            dBException2 -> dBMsgException2
            dBException3 -> dBMsgException3
            dBException4 -> dBMsgException4
            dBException5 -> dBMsgException5
            ///
            userLDException1 -> userLDMsgException1
            else -> unknownException
        }
    }

    fun getStrIntentMsgException(
        numException: Int
    ): String {
        return when (numException) {
            1 -> intentMsgException1
            2 -> intentMsgException2
            3 -> intentMsgException3
            4 -> intentMsgException4
            5 -> intentMsgException5
            else -> unknownException
        }
    }

}