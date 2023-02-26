package com.example.dailyforecast.dataSourse.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dailyforecast.model.WeatherResponseRoom
import com.example.dailyforecast.utils.Converter

@Database(entities = [WeatherResponseRoom::class], version = 5)
@TypeConverters(Converter::class)
abstract class DailyForecastDataBase : RoomDatabase() {
    abstract fun dailyForecastDataBaseDao(): DailyForeCastDao

}