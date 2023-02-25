package com.example.dailyforecast.ui

import androidx.lifecycle.*
import com.example.dailyforecast.model.WeatherResponse
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
    val errorMessage: MutableLiveData<String
    > get() = _errorMessage

    private var _cityForecast = MutableLiveData<
            Resource<ArrayList<WeatherResponse.WeatherList>, WeatherResponse.WeatherCity>>()
    var cityForecast: LiveData<
            Resource<ArrayList<WeatherResponse.WeatherList>, WeatherResponse.WeatherCity>
            > = _cityForecast


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


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}