package com.example.dailyforecast.dataSourse.remote

import com.example.dailyforecast.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("forecast")
    suspend fun getResponse(
        @Query("appid") apiKey: String,
        @Query("q") city:String,
        ):Response<WeatherResponse<ArrayList<WeatherResponse.WeatherList>,
            WeatherResponse.WeatherCity>>


}