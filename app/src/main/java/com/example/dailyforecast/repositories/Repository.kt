package com.example.dailyforecast.repositories

import com.example.dailyforecast.dataSourse.remote.ApiHelper
import com.example.dailyforecast.dataSourse.remote.ApiHelperImpl
import com.example.dailyforecast.utils.Resource
import retrofit2.Response
import javax.inject.Inject


class Repository  @Inject constructor(private var api: ApiHelper)
{
    private val appid:String= "77b545e4e1b6c4db7bd4291d5cf5be08"

     suspend fun getCityForecast(city:String)=api.getResponse(appid,city)

}