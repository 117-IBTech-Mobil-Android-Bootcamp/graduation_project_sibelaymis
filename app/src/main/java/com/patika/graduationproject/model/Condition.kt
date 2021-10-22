package com.patika.graduationproject.model

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val weatherState:String,
    @SerializedName("icon") val weatherIconUrl:String
)
