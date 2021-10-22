package com.patika.graduationproject.model

import com.patika.graduationproject.service.response.WeatherDetailResponse


class WeatherStateModel(private val weather: WeatherDetailResponse) {
    fun getList(): List<Current> = weather.forecast.forecastDayList[0].hour
}