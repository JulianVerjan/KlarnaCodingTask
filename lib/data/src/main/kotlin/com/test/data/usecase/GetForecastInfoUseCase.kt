package com.test.data.usecase

import com.test.data.repository.WeatherRepository
import javax.inject.Inject

class GetForecastInfoUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun fetchForecastInfo(
        latitude: Double,
        longitude: Double
    ) =
        weatherRepository.fetchForecastInfo(latitude, longitude)
}
