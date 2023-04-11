package dev.mehdi.bakhtiari.presfeed.utils

import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.concurrent.Executors
import javax.inject.Inject

class FingerprintAuthenticationCallback @Inject constructor(
    private val context: Context
) {

    private var authenticationCallback: ((Boolean) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.P)
    fun startAuthentication(callback: (Boolean) -> Unit) {
        authenticationCallback = callback

        val biometricPrompt = BiometricPrompt.Builder(context)
            .setTitle("Fingerprint Validation")
            .setDescription("To access touch the fingerprint sensor")
            .setNegativeButton("Cancel", Executors.newSingleThreadExecutor()
            ) { _, _ -> }
            .build()

        val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                handleAuthenticationResult(false)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                handleAuthenticationResult(true)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                handleAuthenticationResult(false)
            }
        }

    }

    private fun handleAuthenticationResult(isSuccess: Boolean) {
        authenticationCallback?.invoke(isSuccess)
    }
}