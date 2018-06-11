package ru.sberleasing.model.data

data class WeatherResponse(val main : Weather)


data class Weather(val temp: Double, val humidity: Int)