package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.coredata.dataModule
import com.picpay.desafio.android.di.contactModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    contactModule,
                    dataModule
                )
            )
        }
    }
}