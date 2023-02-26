package com.example.dailyforecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DailyForecast_Table")
class WeatherResponseRoom(
    @ColumnInfo(name ="weather")
    var weatherList: List<WeatherList> = listOf (WeatherList("",0,0.0,0, Clouds("")
        , Main(0.0,0.0,0.0,0.0,0,0,0,0,0f)
        ,listOf(Weather(0,",","")), Wind(0f,0,0f), Sys("")
    )) as ArrayList<WeatherList>,
    @PrimaryKey
    @ColumnInfo(name ="city")
    var weatherCity: WeatherCity=WeatherCity(0,"",Coord(0.0,0.0),"",0,0,0,0)

) {
}