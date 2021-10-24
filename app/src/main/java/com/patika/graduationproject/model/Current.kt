package com.patika.graduationproject.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CURRENT_WEATHER")
data class Current(
    @ColumnInfo(name = "celsiusTemp") @SerializedName("temp_c") val celsiusTemp:Double,
    @ColumnInfo(name = "fahrenheitTemp") @SerializedName("temp_f") val fahrenheitTemp:Double,
    @ColumnInfo(name = "feelsCelsiusTemp") @SerializedName("feelslike_c") val feelsCelsiusTemp:Double,
    @ColumnInfo(name = "feelsFahrenheitTemp") @SerializedName("feelslike_f") val feelsFahrenheitTemp:Double,
    @ColumnInfo(name = "time") @SerializedName("time") var time:String="",
    @PrimaryKey @ColumnInfo(name = "name") var name:String="",
    @ColumnInfo(name = "condition") val condition: Condition
)
