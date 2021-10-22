package com.patika.graduationproject.service.response

import com.patika.graduationproject.model.Current
import com.patika.graduationproject.model.Location

data class CurrentWeatherResponse(
    val location: Location,
    val current: Current
)
