package com.example.dailyforecast.repositories

import com.example.dailyforecast.dataSourse.local.DailyForeCastDao
import com.example.dailyforecast.dataSourse.remote.ApiHelper
import com.example.dailyforecast.model.WeatherResponseRoom
import javax.inject.Inject


class Repository @Inject constructor(
    private var api: ApiHelper,
    private val channelDao: DailyForeCastDao
) {
    private val appid: String = "77b545e4e1b6c4db7bd4291d5cf5be08"

    suspend fun getCityForecast(city: String) = api.getResponse(appid, city)

    fun getSavedForecast(cityName: String) =
        channelDao.getDailyForecastData(cityName)

    suspend fun saveForecast(weatherResponse: WeatherResponseRoom) =
        channelDao.insertDailyForecastData(weatherResponse)
}