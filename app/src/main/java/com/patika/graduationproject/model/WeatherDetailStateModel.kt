package com.patika.graduationproject.model


class WeatherDetailStateModel(private val weatherDetail:Current){
    fun getTempCelsius()=weatherDetail.celsiusTemp.toString()
    fun getTempFahrenheit()=weatherDetail.fahrenheitTemp.toString()
    fun getTempCelsiusFeels()=weatherDetail.feelsCelsiusTemp.toString()
    fun getTempFahrenheitFeels()=weatherDetail.feelsFahrenheitTemp.toString()
    fun getWeatherText()=weatherDetail.condition.weatherState
    private val iconSize=weatherDetail.condition.weatherIconUrl.length
    fun getWeatherStateIconUrl()="http://"+weatherDetail.condition.weatherIconUrl.subSequence(2,iconSize).toString()
    fun getTime()="Date: "+weatherDetail.time
}