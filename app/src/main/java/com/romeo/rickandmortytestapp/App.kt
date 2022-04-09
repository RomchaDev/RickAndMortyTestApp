package com.romeo.rickandmortytestapp

import androidx.multidex.MultiDexApplication
import com.romeo.sign_up_log_in.koin.signUpLogInModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                signUpLogInModule
            )
        }
    }
}