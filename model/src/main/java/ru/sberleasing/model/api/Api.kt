package ru.sberleasing.model.api

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.reactive.awaitFirst
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

    /**
     * The function maps every city in the DB to a Deferred.
     * If we might not need weather for every city, async may be lazy
     */
    suspend fun getWeatherForSavedCities(): Map<City, Deferred<Weather>> {
        return cityDao.getAllCities() // get rx flowable from room
                .awaitFirst() // suspend for first
                .associate {  it to  async { getWeatherForCity(it.name) } }
    }

}
