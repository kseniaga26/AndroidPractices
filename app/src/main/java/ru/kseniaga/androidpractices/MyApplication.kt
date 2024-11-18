package ru.kseniaga.androidpractices

import android.app.Application
import ru.kseniaga.androidpractices.di.networkModule
import ru.kseniaga.androidpractices.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(rootModule, networkModule)
        }
    }
}