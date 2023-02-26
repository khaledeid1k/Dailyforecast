package com.example.dailyforecast.dataSourse.remote

import com.example.dailyforecast.model.WeatherCity
import com.example.dailyforecast.model.WeatherList
import com.example.dailyforecast.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: Api
) : ApiHelper {
    override suspend fun getResponse(apiKey: String,
                                     city:String):
            Response<WeatherResponse<ArrayList
            <WeatherList>, WeatherCity>>
            =
        apiService.getResponse(apiKey,city)
}