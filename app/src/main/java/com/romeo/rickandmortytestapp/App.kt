package com.romeo.rickandmortytestapp

import androidx.multidex.MultiDexApplication
import com.romeo.core.koin.apiModule
import com.romeo.core.koin.constantsModule
import com.romeo.core.koin.coreModule
import com.romeo.core.koin.realmModule
import com.romeo.sign_up_log_in.koin.signUpLogInModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                signUpLogInModule,
                coreModule,
                apiModule,
                constantsModule,
                realmModule
            )
        }

        Realm.init(this)
    }
}