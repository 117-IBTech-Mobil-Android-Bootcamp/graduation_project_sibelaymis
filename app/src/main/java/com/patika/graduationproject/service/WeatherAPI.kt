package com.patika.graduationproject.service

import com.patika.graduationproject.model.City
import com.patika.graduationproject.service.response.CurrentWeatherResponse
import com.patika.graduationproject.service.response.SearchedWeatherResponse
import com.patika.graduationproject.service.response.WeatherDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WeatherAPI {

    @GET("current.json")
    suspend fun getCurrentWeather(@QueryMap params : MutableMap<String, Any>) : CurrentWeatherResponse?

    @GET("forecast.json")
    suspend fun getDetails(@QueryMap params : MutableMap<String, Any>) : WeatherDetailResponse?

    @GET("search.json")
    suspend fun getSearchedWeather(@QueryMap params : MutableMap<String, Any>) : List<City>

}