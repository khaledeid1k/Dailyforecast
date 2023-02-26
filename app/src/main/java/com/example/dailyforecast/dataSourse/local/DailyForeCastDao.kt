package com.example.dailyforecast.dataSourse.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailyforecast.model.WeatherResponseRoom

@Dao
interface DailyForeCastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyForecastData(weatherResponse: WeatherResponseRoom)

    @Query("SELECT * FROM DailyForecast_Table WHERE city LIKE '%' ||:cityName||'%'")
    fun getDailyForecastData(cityName: String): LiveData<WeatherResponseRoom>
}