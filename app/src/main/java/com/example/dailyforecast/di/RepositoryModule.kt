package com.example.dailyforecast.di

import com.example.dailyforecast.dataSourse.local.DailyForeCastDao
import com.example.dailyforecast.dataSourse.remote.ApiHelper
import com.example.dailyforecast.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideRepository(apiHelper: ApiHelper, forecastDao: DailyForeCastDao) =
        Repository(apiHelper, forecastDao)


}