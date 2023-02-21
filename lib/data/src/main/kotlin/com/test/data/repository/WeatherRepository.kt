package com.test.data.repository

import com.test.data.source.WeatherRemoteDataSource
import com.test.model.Forecast
import com.test.model.CurrentWeatherInfo
import com.test.network.model.mapper.NetworkResult
import com.test.network.model.mapper.toForecastModel
import com.test.network.model.mapper.toRepositoryResult
import com.test.network.model.mapper.toCurrentWeatherInfoModel
import com.test.network.model.reponse.ForecastResponse
import com.test.network.model.reponse.CurrentWeatherInfoResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {

    suspend fun fetchForecastInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResult<Forecast> =
        weatherRemoteDataSource.fetchForecastInfo(latitude, longitude)
            .toRepositoryResult(ForecastResponse::toForecastModel)

    suspend fun fetchCurrentWeatherInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResult<CurrentWeatherInfo> =
        weatherRemoteDataSource.fetchCurrentWeatherInfo(latitude, longitude)
            .toRepositoryResult(CurrentWeatherInfoResponse::toCurrentWeatherInfoModel)

}
