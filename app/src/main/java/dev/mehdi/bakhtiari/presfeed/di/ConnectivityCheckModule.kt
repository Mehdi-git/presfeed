package dev.mehdi.bakhtiari.presfeed.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.mehdi.bakhtiari.presfeed.utils.NetworkConnectivityChecker
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ConnectivityCheckModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityChecker(connectivityManager: ConnectivityManager): NetworkConnectivityChecker {
        return NetworkConnectivityChecker(connectivityManager)
    }
}

