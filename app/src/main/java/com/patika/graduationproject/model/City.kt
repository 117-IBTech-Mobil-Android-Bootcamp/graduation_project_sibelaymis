package com.patika.graduationproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    @Expose
    val id:Int,
    @SerializedName("name")
    @Expose
    val name:String,
    @SerializedName("region")
    @Expose
    val region:String,
    @SerializedName("county")
    @Expose
    val county:String,
    @SerializedName("lat")
    @Expose
    val lat:Float,
    @SerializedName("lon")
    @Expose
    val lon:Float,
    @SerializedName("url")
    @Expose
    val url:String,
)
