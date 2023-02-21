package com.test.weather.ui.weatherscreen

import com.test.model.Forecast
import com.test.model.CurrentWeatherInfo

data class WeatherInfoState(
    val uiState: UIState = UIState.IDLE,
    val forecastInfo: Forecast? = null,
    val currentWeatherInfo: CurrentWeatherInfo? = null
)
