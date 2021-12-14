package com.daveloper.soccerapp.auxiliar.internet_conection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import javax.inject.Inject

class InternetConnection @Inject constructor(
    private val giveMeAppContext: Context
){
    fun internetIsConnected (
    ) : Boolean {
        var context = giveMeAppContext
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: Network? = connectivityManager.activeNetwork
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(activeNetwork)

        if (networkCapabilities != null) {
            return when {
                // WIFI Network
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                // MOBILE DATA Network
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            return false
        }
    }
}