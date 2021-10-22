package com.patika.graduationproject.model

import com.patika.graduationproject.service.response.CurrentWeatherResponse

class CurrentWeatherStateModel(private val currentWeatherResponse: CurrentWeatherResponse) {
    fun getName()=currentWeatherResponse.location.name
    fun getLocalTime()=currentWeatherResponse.location.localtime.subSequence(0,10)
    fun getTempCelsius()=currentWeatherResponse.current.celsiusTemp.toString()
    fun getTempFahrenheit()=currentWeatherResponse.current.fahrenheitTemp.toString()
    fun getTempCelsiusFeels()=currentWeatherResponse.current.feelsCelsiusTemp.toString()
    fun getTempFahrenheitFeels()=currentWeatherResponse.current.feelsFahrenheitTemp.toString()
    fun getWeatherText()=currentWeatherResponse.current.condition.weatherState
    private val iconSize=currentWeatherResponse.current.condition.weatherIconUrl.length
    fun getWeatherStateIconUrl()="http://"+currentWeatherResponse.current.condition.weatherIconUrl.subSequence(2,iconSize).toString()
    fun getLocation()=currentWeatherResponse.location
    fun getCurrent()=currentWeatherResponse.current
}