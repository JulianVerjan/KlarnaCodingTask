package com.test.data.source

import com.test.network.service.WeatherApi
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun fetchForecastInfo(latitude: Double, longitude: Double) =
        weatherApi.fetchForecastInfo(latitude, longitude)

    suspend fun fetchCurrentWeatherInfo(latitude: Double, longitude: Double) =
        weatherApi.fetchCurrentWeatherInfo(latitude, longitude)
}
