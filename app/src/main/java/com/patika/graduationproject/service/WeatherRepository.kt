package com.patika.graduationproject.service

import com.patika.graduationproject.service.response.CurrentWeatherResponse
import com.patika.graduationproject.service.response.WeatherDetailResponse
import com.patika.graduationproject.util.API_KEY
import com.patika.graduationproject.Result
import com.patika.graduationproject.db.WeatherDao
import com.patika.graduationproject.model.City
import com.patika.graduationproject.model.Current
import com.patika.graduationproject.model.Location
import com.patika.graduationproject.service.response.CurrentWeatherListResponse

class WeatherRepository(private val api: WeatherAPI, private val weatherDao: WeatherDao) {

    suspend fun getCurrentWeather(name: String): Result<CurrentWeatherResponse> {

        val params = mutableMapOf<String, Any>().apply {
            put("key", API_KEY)
            put("q", name)
            put("aqi", "no")
            put("alerts", "no")
        }
        val response = api.getCurrentWeather(params)
        return if (response != null) {
            response.current.time=response.location.localtime
            response.current.name=response.location.name
            insertDataAsync(response.current)
            Result.Success(response)
        } else {
            Result.Error("Bir hata meydana geldi.")
        }
    }
    suspend fun insertDataAsync(current: Current) = weatherDao.insertCurrentWeather(current)

    suspend fun getListAsync(): CurrentWeatherListResponse {
        val weatherList = weatherDao.fetchCurrentWeathers()
        var currentWeatherListResponse= mutableListOf<CurrentWeatherResponse>()
        weatherList.forEach {
            currentWeatherListResponse.add(CurrentWeatherResponse(Location(it.name,it.time),it))
        }
        return CurrentWeatherListResponse(currentWeatherListResponse)
    }
    suspend fun getWeatherDetail(name: String): Result<WeatherDetailResponse> {

        val params = mutableMapOf<String, Any>().apply {
            put("key", API_KEY)
            put("q", name)
            put("days", "1")
            put("aqi", "no")
            put("alerts", "no")
        }
        val response = api.getDetails(params)
        return if (response != null) {
            Result.Success(response)
        } else {
            Result.Error("Bir hata meydana geldi.")
        }
    }

    suspend fun getSearchedWeather(name: String): Result<List<City>> {

        val params = mutableMapOf<String, Any>().apply {
            put("key", API_KEY)
            put("q", name)
        }
        val response = api.getSearchedWeather(params)
        return if (response != null) {
            Result.Success(response)
        } else {
            Result.Error("Bir hata meydana geldi.")
        }
    }
}