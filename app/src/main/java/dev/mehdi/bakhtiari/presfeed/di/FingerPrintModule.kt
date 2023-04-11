package dev.mehdi.bakhtiari.presfeed.di

import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mehdi.bakhtiari.presfeed.R
import dev.mehdi.bakhtiari.presfeed.utils.FingerprintAuthenticationCallback
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
object FingerPrintModule {

    @RequiresApi(Build.VERSION_CODES.P)
    @Provides
    fun provideBiometricPrompt(context: Context): BiometricPrompt {
        return BiometricPrompt.Builder(context)
            .setTitle(context.getString(R.string.fingerprint_validation))
            .setDescription((context.getString(R.string.fingerprint_description)))
            .setNegativeButton((context.getString(R.string.fingerprint_Cancel)),
                Executors.newSingleThreadExecutor()
            ) { _, _ -> }
            .build()
    }

    @Provides
    fun provideFingerprintAuthenticationCallback(context: Context): FingerprintAuthenticationCallback {
        return FingerprintAuthenticationCallback(context)
    }
}

