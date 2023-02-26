package com.example.dailyforecast.utils

import androidx.room.TypeConverter
import com.example.dailyforecast.model.WeatherCity
import com.example.dailyforecast.model.WeatherList
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromWeatherListToJson(value: List<WeatherList>?) = Gson().toJson(value)!!

    @TypeConverter
    fun fromJsonToWeatherList(value: String) =
        Gson().fromJson(value, Array<WeatherList>::class.java).toList()

    @TypeConverter
    fun fromWeatherCityToJson(value: WeatherCity?) = Gson().toJson(value)!!

    @TypeConverter
    fun fromGsonToWeatherCity(value: String) = Gson().fromJson(value, WeatherCity::class.java)!!

}