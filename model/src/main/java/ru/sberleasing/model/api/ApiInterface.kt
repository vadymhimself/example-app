package ru.sberleasing.model.api

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sberleasing.model.data.WeatherResponse

internal interface ApiInterface {

    @GET("weather")
    fun getWeatherForQuery(@Query("q") query: String) : Deferred<WeatherResponse>
}
