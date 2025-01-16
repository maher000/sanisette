package com.maher.sanisettes

import android.app.Application
import com.maher.sanisettes.di.appModule
import com.maher.sanisettes.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class SanisetteApplication : Application(), KoinStartup {

    override fun onKoinStartup(): KoinConfiguration =
        KoinConfiguration {
            androidLogger()
            androidContext(this@SanisetteApplication)
            modules(appModule, networkModule)
        }
}