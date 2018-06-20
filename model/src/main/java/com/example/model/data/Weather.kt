package com.example.model.data

data class WeatherResponse(val main : Weather)


data class Weather(val temp: Double, val humidity: Int)