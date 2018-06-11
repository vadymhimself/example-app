package ru.sberleasing.model.api

import kotlinx.coroutines.experimental.delay
import ru.sberleasing.model.data.Weather
import javax.inject.Inject

class Api @Inject internal constructor(
        private val apiInterface: ApiInterface
) {

    suspend fun getWeatherForCity(cityName: String) : Weather {
        delay(2000)
        return apiInterface.getWeatherForQuery(cityName).await().main
    }
}
