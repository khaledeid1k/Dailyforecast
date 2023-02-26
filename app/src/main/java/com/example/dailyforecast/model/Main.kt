package com.example.dailyforecast.model

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
