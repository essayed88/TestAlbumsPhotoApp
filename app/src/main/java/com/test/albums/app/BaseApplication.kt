package com.test.albums.app

import android.app.Application
import com.test.albums.di.applicationModule
import com.test.albums.di.networkModule
import com.test.albums.di.roomModule
import com.test.albums.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(
                listOf(
                    applicationModule,
                    networkModule,
                    roomModule,
                    viewModelModule
                )
            )
        }
    }
}