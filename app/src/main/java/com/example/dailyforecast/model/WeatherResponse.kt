package com.example.dailyforecast.model

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

data class WeatherResponse<T, Y>(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: T?,
    val city: Y?
) {
    companion object {
        @BindingAdapter("SetWindSpeed")
        @JvmStatic
        fun setWindSpeed(view: View, speed: Double) {
            val textview: TextView = view as TextView
            val speed1 = speed.times(1000)
                .roundToInt()
                .div(1000.0).toFloat()
            textview.text = speed1.toString()
        }

    }


}