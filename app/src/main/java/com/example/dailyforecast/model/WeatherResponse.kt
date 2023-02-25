package com.example.dailyforecast.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

data class WeatherResponse<T, Y>(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: T?,
    val city: Y?
) {

    data class WeatherList(

        @SerializedName("dt_txt")
        var date: String = "",
        var visibility: Long = 0,
        var pop: Double = 0.0,
        var dt: Long = 0,
        var clouds: Clouds = Clouds(""),
        var main: Main = Main(0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0f),
        var weather: List<Weather> = listOf(Weather(0, ",", "")),
        var wind: Wind = Wind(0f, 0, 0f),
        var sys: Sys = Sys(""),
    )

    data class Sys(
        var pod: String = ""
    )

    data class Wind(
        var speed: Float,
        var deg: Long,
        var gust: Float
    )

    data class Clouds(
        var all: String
    )
    data class Weather(
        var id: Long,
        var main: String,
        var description: String,
    )
    data class Main (
        var temp: Double,
        var feels_like: Double,
        var temp_min: Double,
        var temp_max: Double,
        var  pressure: Long,
        var  sea_level: Long,
        var  grnd_level: Long,
        var  humidity: Int,
        var  temp_kf : Float
    )

    companion object{
        @BindingAdapter("SetWindSpeed")
        @JvmStatic
        fun setWindSpeed(view: View, speed: Double) {
            val textview : TextView = view as TextView
            val speed1=speed.times(1000)
                .roundToInt()
                .div(1000.0).toFloat()
            textview.text= speed1.toString()
        }
    }
    data class WeatherCity(
        var  id: Long=0,
        var  name: String="",
        var coord : Coord = Coord(0.0,0.0),
        var country : String="",
        var population :Long=0,
        var timezone: Long=0,
        var sunrise: Long=0,
        var sunset:Long=0
    )
    data class Coord (

        var lat : Double=0.0,
        var  lon : Double=0.0

    )

}