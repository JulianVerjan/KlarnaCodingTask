package com.test.data.usecase

import com.test.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherOnLocationInfoUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun fetchWeatherOnLocationInfo(
        latitude: Double,
        longitude: Double
    ) =
        weatherRepository.fetchWeatherOnLocationInfo(latitude, longitude)

}