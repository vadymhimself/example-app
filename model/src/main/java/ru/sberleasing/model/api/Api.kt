package ru.sberleasing.model.api

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.rx2.await
import ru.sberleasing.model.data.Weather
import ru.sberleasing.model.persistance.City
import ru.sberleasing.model.persistance.CityDao
import javax.inject.Inject

class Api @Inject internal constructor(
        private val apiInterface: ApiInterface,
        private val cityDao: CityDao
) {
    suspend fun getWeatherForCity(cityName: String) : Weather {
        delay(2000)
        return apiInterface.getWeatherForQuery(cityName).main
    }

    suspend fun getWeatherForSavedCities(): Map<City, Weather> {
        return cityDao.getAllCities().firstOrError().await()
                .map { it to apiInterface.getWeatherForQuery(it.name).main }
                .toMap()
    }
}
