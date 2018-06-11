package com.example

import android.databinding.ObservableBoolean
import android.widget.Toast
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutWeatherBinding
import com.example.domain.App

class WeatherController : Controller<LayoutWeatherBinding>() {

    val progress = ObservableBoolean()

    init {
        async {
            val weather = App.require().api().getWeatherForCity("Chicago, IL")
            Toast.makeText(App.getContext(), "${weather.temp}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_weather
}