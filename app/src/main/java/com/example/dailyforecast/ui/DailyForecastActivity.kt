package com.example.dailyforecast.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyforecast.adapter.WeatherAdapter
import com.example.dailyforecast.databinding.ActivityMainBinding
import com.example.dailyforecast.model.WeatherList
import com.example.dailyforecast.model.WeatherResponseRoom
import com.example.dailyforecast.utils.makeGone
import com.example.dailyforecast.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyForecastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherForecastAdapter: WeatherAdapter
    private val weatherDailyForecastViewModel: DailyForecastViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        searchResult()
        retry()
        getCachedData()

    }

    private fun getData() {
        binding.noInternet.makeGone()
        binding.showCachedData.makeGone()
        binding.noAccurateData.makeGone()
        binding.recyclerview.makeVisible()
        val query = binding.search.text.toString()
        weatherDailyForecastViewModel.getCityForecast(query)
        observeResponse()


    }

    private fun retry() {
        binding.retry.setOnClickListener {
            if (!checkForInternet(this)) {
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
            } else {
                refresh()
                getData()
            }

        }
    }

    private fun searchResult() {

        binding.search.setOnEditorActionListener { _, _, _ ->
            val query = binding.search.text.toString()
            if (query.isEmpty()) {
                Toast.makeText(this, "Please ,Enter city name ", Toast.LENGTH_SHORT).show()
            } else {
                if (checkForInternet(this)) {
                    getData()
                } else {
                    binding.recyclerview.makeGone()
                    binding.noInternet.makeVisible()
                    binding.retry.makeVisible()
                    binding.showCachedData.makeVisible()

                }


            }



            return@setOnEditorActionListener false
        }
    }

    private fun observeResponse() {

        weatherDailyForecastViewModel.cityForecast.observe(this)
        {
            addViews(it.WeatherState!!)
            val weatherResponse = WeatherResponseRoom(it.WeatherState, it.City!!)
            weatherDailyForecastViewModel.saveDailyForecast(weatherResponse)

        }
    }

    private fun getCachedData() {
        binding.showCachedData.setOnClickListener {
            binding.noInternet.makeGone()
            binding.recyclerview.makeVisible()
            val searchedCity = binding.search.text.toString()
            weatherDailyForecastViewModel.getSavedDailyForecast(searchedCity)
            observeRoomResponse()
        }

    }

    private fun observeRoomResponse() {
        weatherDailyForecastViewModel.city.observe(this) {
            val list = (it.weatherList)
            if (list.isNotEmpty()) {

                binding.noAccurateData.makeVisible()
                binding.noInternet.makeGone()
                binding.recyclerview.makeVisible()
                addViews(java.util.ArrayList(list))
            } else {

                binding.noAccurateData.makeGone()
                binding.noInternet.makeVisible()
                binding.recyclerview.makeGone()

            }
        }
    }

    private fun addViews(weatherList: ArrayList<WeatherList>) {
        weatherForecastAdapter = WeatherAdapter(weatherList)
        binding.recyclerview.adapter = weatherForecastAdapter

    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun refresh() {
        val intent = Intent(applicationContext, DailyForecastActivity::class.java)
        startActivity(intent)
        finish()
    }
}