package com.patika.graduationproject.di

import androidx.room.Room
import com.patika.graduationproject.db.WeatherDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "DB"
val dbModule = module {

    // Room Database
    single { Room.databaseBuilder(androidContext(), WeatherDB::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build() }

    // WeatherDao
    single { get<WeatherDB>().weatherDao() }
}