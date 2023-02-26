package com.example.dailyforecast.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
