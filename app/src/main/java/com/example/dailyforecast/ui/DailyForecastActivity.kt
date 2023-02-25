package com.example.dailyforecast.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyforecast.adapter.WeatherAdapter
import com.example.dailyforecast.databinding.ActivityMainBinding
import com.example.dailyforecast.model.WeatherResponse
import com.example.dailyforecast.utils.makeGone
import com.example.dailyforecast.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyForecastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherForecastAdapter: WeatherAdapter
    private val weatherForecastViewModel: DailyForecastViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        searchResult()
        retry()
    }

    fun getData(){
        val query = binding.search.text.toString()
        weatherForecastViewModel.getCityForecast(query)
        observeResponse()
        binding.recyclerview.makeVisible()

    }
    private fun retry(){
        binding.retry.setOnClickListener {
            if(!checkForInternet(this)){
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
            }else{
                binding.noInternet.makeGone()
                binding.retry.makeGone()
                getData()
            }

        }
    }
    private fun searchResult() {

        binding.search.setOnEditorActionListener { _, _, _ ->
            val query = binding.search.text.toString()
            if (query.isEmpty()) {
                binding.recyclerview.makeGone()
                Toast.makeText(this, "Please ,Enter city name ", Toast.LENGTH_SHORT).show()
            } else {
                if(checkForInternet(this)){
                    getData()
                }else{
                    binding.recyclerview.makeGone()
                    binding.noInternet.makeVisible()
                    binding.retry.makeVisible()
                }



            }



            return@setOnEditorActionListener false
        }
    }

    private fun observeResponse() {

        weatherForecastViewModel.cityForecast.observe(this)
        {
                    binding.recyclerview.makeVisible()
                    addViews(it.WeatherState!!)


        }
    }

    private fun addViews(weatherList: ArrayList<WeatherResponse.WeatherList>) {
        weatherForecastAdapter = WeatherAdapter(weatherList)
        binding.recyclerview.adapter = weatherForecastAdapter

    }
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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
}