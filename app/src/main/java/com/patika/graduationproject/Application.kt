package com.patika.graduationproject

import android.app.Application
import com.patika.graduationproject.di.dbModule
import com.patika.graduationproject.di.networkModule
import com.patika.graduationproject.di.repositoryModule
import com.patika.graduationproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(networkModule, repositoryModule, viewModelModule, dbModule)
        }
    }
}