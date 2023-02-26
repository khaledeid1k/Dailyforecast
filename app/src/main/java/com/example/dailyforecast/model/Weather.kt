package com.example.dailyforecast.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Weather(
    var id: Long,
    var main: String,
    var description: String,
)
