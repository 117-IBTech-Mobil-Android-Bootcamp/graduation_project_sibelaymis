package com.patika.graduationproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.patika.graduationproject.model.Current

@Dao
interface WeatherDao {

    /*
    *bizim case'imize göre conflict oldugunda, conflict olan row'u
    * yeni gelen row ile replace edecek(değişticek/update edecek)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weather : Current)

    @Query("SELECT * FROM CURRENT_WEATHER")
    suspend fun  fetchCurrentWeathers() : List<Current>
}