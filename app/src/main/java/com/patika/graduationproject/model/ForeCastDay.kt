package com.patika.graduationproject.model

import com.google.gson.annotations.SerializedName

data class ForeCastDay(
    @SerializedName("hour") val hour: List<Current>
)
