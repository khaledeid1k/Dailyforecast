package com.example.dailyforecast.model


data class WeatherList(
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