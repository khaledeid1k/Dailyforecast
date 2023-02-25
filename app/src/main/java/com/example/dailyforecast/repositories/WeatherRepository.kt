package com.example.dailyforecast.repositories

import com.example.dailyforecast.dataSourse.remote.Api
import com.example.dailyforecast.dataSourse.remote.ApiHelper
import com.example.dailyforecast.dataSourse.remote.ApiHelperImpl
import com.example.dailyforecast.utils.Resource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherRepository {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}