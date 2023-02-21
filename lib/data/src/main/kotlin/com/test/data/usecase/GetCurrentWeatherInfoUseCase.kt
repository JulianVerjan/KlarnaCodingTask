package com.test.data.usecase

import com.test.data.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherInfoUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun fetchCurrentWeatherInfo(
        latitude: Double,
        longitude: Double
    ) = weatherRepository.fetchCurrentWeatherInfo(latitude, longitude)

}