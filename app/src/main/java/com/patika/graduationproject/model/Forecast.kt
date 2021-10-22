package com.patika.graduationproject.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday") val forecastDayList: List<ForeCastDay>
)
