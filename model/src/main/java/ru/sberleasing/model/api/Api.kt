package ru.sberleasing.model.api

import ru.sberleasing.model.data.Weather
import javax.inject.Inject

class Api @Inject internal constructor(
        private val apiInterface: ApiInterface
) {

    suspend fun getWeatherForCity(cityName: String) : Weather {
        return apiInterface.getWeatherForQuery(cityName).await().main
    }
}
