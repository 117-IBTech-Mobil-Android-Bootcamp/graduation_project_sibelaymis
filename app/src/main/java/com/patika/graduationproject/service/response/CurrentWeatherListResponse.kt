package com.patika.graduationproject.service.response

import com.patika.graduationproject.model.Current
import com.patika.graduationproject.model.Location

data class CurrentWeatherListResponse(
    val currentWeatherList: List<CurrentWeatherResponse>
)
