package com.example.dailyforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyforecast.R
import com.example.dailyforecast.databinding.SearchItemBinding
import com.example.dailyforecast.model.WeatherList

class WeatherAdapter(var weatherList: ArrayList<WeatherList>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView: SearchItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var searchItemBinding: SearchItemBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groceriesItemBinding: SearchItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.search_item, parent, false
        )
        return ViewHolder(groceriesItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productList = weatherList[position]
        holder.searchItemBinding.model = productList
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

}