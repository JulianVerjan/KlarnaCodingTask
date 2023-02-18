package com.test.data.repository

import com.test.data.source.WeatherRemoteDataSource
import com.test.model.Forecast
import com.test.model.WeatherOnLocation
import com.test.network.model.mapper.NetworkResultResult
import com.test.network.model.mapper.toForecastModel
import com.test.network.model.mapper.toRepositoryResult
import com.test.network.model.mapper.toWeatherOnLocationModel
import com.test.network.model.reponse.ForecastResponse
import com.test.network.model.reponse.WeatherOnLocationResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {

    suspend fun fetchForecastInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResultResult<Forecast> =
        weatherRemoteDataSource.fetchForecastInfo(latitude, longitude)
            .toRepositoryResult(ForecastResponse::toForecastModel)

    suspend fun fetchWeatherOnLocationInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResultResult<WeatherOnLocation> =
        weatherRemoteDataSource.fetchWeatherOnLocationInfo(latitude, longitude)
            .toRepositoryResult(WeatherOnLocationResponse::toWeatherOnLocationModel)

}
