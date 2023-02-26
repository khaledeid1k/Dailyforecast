package com.example.dailyforecast.dataSourse.remote

import com.example.dailyforecast.model.WeatherCity
import com.example.dailyforecast.model.WeatherList
import com.example.dailyforecast.model.WeatherResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getResponse(apiKey: String,
                            city:String):
  Response<WeatherResponse<ArrayList<
          WeatherList>, WeatherCity>>


}