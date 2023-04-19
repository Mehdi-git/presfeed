package dev.mehdi.bakhtiari.presfeed.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Singleton

@Singleton
class NetworkConnectivityChecker(private val connectivityManager: ConnectivityManager) {

    fun isNetworkAvailable(): Boolean {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}