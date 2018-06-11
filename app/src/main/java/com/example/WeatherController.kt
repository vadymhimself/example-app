package com.example

import android.databinding.ObservableBoolean
import com.controllers.Controller
import com.controllers.async
import com.example.databinding.LayoutWeatherBinding
import com.example.domain.App
import com.example.misc.handleError

class WeatherController : Controller<LayoutWeatherBinding>() {

    val progress = ObservableBoolean()

    init {
        async {
            progress.set(true)
            try {
                val weather = App.require().api().getWeatherForCity("Chicago")
                binding?.run {
                    tvTemp.text = "${weather.temp} C"
                }
            } catch (e: Exception) {
                handleError(e)
            } finally {
                progress.set(false)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_weather
}