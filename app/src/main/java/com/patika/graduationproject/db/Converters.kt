package com.patika.graduationproject.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patika.graduationproject.model.Condition

class Converters {

    @TypeConverter
    fun fromCondition(condition: Condition):String{
        val type = object : TypeToken<Condition>() {}.type
        return Gson().toJson(condition, type)
    }

    @TypeConverter
    fun toCondition(condition: String):Condition{
        val type = object : TypeToken<Condition>() {}.type
        return Gson().fromJson<Condition>(condition, type)
    }
}