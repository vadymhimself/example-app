package com.example

import android.databinding.ObservableBoolean
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutWeatherBinding
import com.example.domain.App

class WeatherController : Controller<LayoutWeatherBinding>() {

    val progress = ObservableBoolean()

    init {
        async {
            try {
                progress.set(true)
                val cities = App.require().api().getWeatherForSavedCities()
                binding?.tvTemp?.text = "${cities.values.map { it.await().temp }} C"
            } finally {
                progress.set(false)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_weather
}