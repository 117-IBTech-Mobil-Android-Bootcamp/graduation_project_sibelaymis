package com.patika.graduationproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.patika.graduationproject.model.Current

@Database(entities = [Current::class], version = 4)
@TypeConverters(Converters::class)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao

}