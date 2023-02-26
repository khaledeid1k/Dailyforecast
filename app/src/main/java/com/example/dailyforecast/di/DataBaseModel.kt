package com.example.dailyforecast.di

import android.content.Context
import androidx.room.Room
import com.example.dailyforecast.dataSourse.local.DailyForeCastDao
import com.example.dailyforecast.dataSourse.local.DailyForecastDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModel {
    @Provides
    @Singleton
    fun provideDailyForecastDatabase(@ApplicationContext applicationContext: Context)
            : DailyForecastDataBase {
        return Room.databaseBuilder(
            applicationContext,
            DailyForecastDataBase::class.java,
            "forecast_Table"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDailyForecastForecastDao(foreCastDatabase: DailyForecastDataBase): DailyForeCastDao {
        return foreCastDatabase.dailyForecastDataBaseDao()
    }
}