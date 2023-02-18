package com.test.network.service

import com.test.model.Forecast
import com.test.model.WeatherOnLocation
import com.test.network.model.NetworkResponse
import com.test.network.model.reponse.ForecastResponse
import com.test.network.model.reponse.WeatherOnLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun fetchForecastInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): NetworkResponse<ForecastResponse, Forecast>


    @GET("weather")
    suspend fun fetchWeatherOnLocationInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): NetworkResponse<WeatherOnLocationResponse, WeatherOnLocation>
}
