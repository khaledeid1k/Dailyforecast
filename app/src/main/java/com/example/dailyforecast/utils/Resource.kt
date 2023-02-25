package com.example.dailyforecast.utils

import com.example.dailyforecast.model.Cod

data class Resource<T, Y>(
    val cod: Cod,
    val message: String? = "",
    val WeatherState: T?,
    val City: Y?
) {
    companion object {
        fun <T, Y> success(WeatherState: T?, City: Y?): Resource<T, Y> {
            return Resource(Cod.OK, null, WeatherState, City)
        }

        fun <T, Y> error(message: String?): Resource<T, Y> {
            return Resource(Cod.ERROR, message, null, null)
        }

    }
}