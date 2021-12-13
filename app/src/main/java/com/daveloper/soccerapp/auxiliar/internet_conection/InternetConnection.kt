package com.daveloper.soccerapp.auxiliar.internet_conection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object InternetConnection {

    @Singleton
    @Provides
    fun internetIsConnected (
        @ApplicationContext context: Context
    ) : Boolean {
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