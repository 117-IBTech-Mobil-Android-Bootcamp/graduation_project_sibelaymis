package com.patika.graduationproject.service.response

import com.google.gson.annotations.SerializedName
import com.patika.graduationproject.model.City

data class SearchedWeatherResponse(
    val cityList: List<City>
)
