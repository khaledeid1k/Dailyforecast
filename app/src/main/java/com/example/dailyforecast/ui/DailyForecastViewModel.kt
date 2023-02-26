package com.example.dailyforecast.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.dailyforecast.model.WeatherCity
import com.example.dailyforecast.model.WeatherList
import com.example.dailyforecast.model.WeatherResponseRoom
import com.example.dailyforecast.repositories.Repository
import com.example.dailyforecast.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor
    (private var repository:Repository): ViewModel() {

    private var _errorMessage = MutableLiveData<
            String>()
    val errorMessage: LiveData<String
    > get() = _errorMessage

    private var _cityForecast = MutableLiveData<
            Resource<ArrayList<WeatherList>, WeatherCity>>()
    var cityForecast: LiveData<
            Resource<ArrayList<WeatherList>, WeatherCity>
            > = _cityForecast
    var city = MediatorLiveData<WeatherResponseRoom>()


    // if i want to cansel Coroutine
    private var job: Job? = null

    // this function get forecast data from Api
    fun getCityForecast(city: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            repository.getCityForecast(city).let {
                withContext(Dispatchers.Main) {
                    if (it.isSuccessful) {
                        _cityForecast.value=Resource.success(it.body()?.list,it.body()?.city)
                    } else {
                        _errorMessage.value= it.errorBody().toString()
                    }
                }
            }

        }
    }

    fun saveDailyForecast (weatherResponse: WeatherResponseRoom) {
        viewModelScope.launch {
            repository.saveForecast (weatherResponse)
        }
    }
    fun getSavedDailyForecast(cityName:String) {
        viewModelScope.launch {
            city.addSource( repository.getSavedForecast(cityName)){
                if(it!=null){
                    city.value= WeatherResponseRoom(it.weatherList,it.weatherCity)

                }else{
                    city.value= WeatherResponseRoom(listOf(), WeatherCity())

                }

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}