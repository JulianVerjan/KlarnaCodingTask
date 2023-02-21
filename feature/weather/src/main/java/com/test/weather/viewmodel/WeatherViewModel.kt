package com.test.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.data.usecase.GetForecastInfoUseCase
import com.test.data.usecase.GetLocationUseCase
import com.test.data.usecase.GetCurrentWeatherInfoUseCase
import com.test.weather.mapper.getFetchedInfo
import com.test.weather.mapper.toForeCastUI
import com.test.weather.mapper.toCurrentWeatherInfoUI
import com.test.weather.ui.weatherscreen.UIState
import com.test.weather.ui.weatherscreen.WeatherInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherInfoUseCase: GetCurrentWeatherInfoUseCase,
    private val getForecastInfoUseCase: GetForecastInfoUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(WeatherInfoState())
    val viewStateFlow: StateFlow<WeatherInfoState> = _viewStateFlow

    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        viewModelScope.launch {
            _viewStateFlow.value = viewStateFlow.value.copy(uiState = UIState.LOADING)
            val currentLocation = getLocationUseCase.getCurrentLocation()
            if (currentLocation?.latitude != null && currentLocation.longitude != null) {
                val latitude = currentLocation.latitude ?: 0.toDouble()
                val longitude = currentLocation.longitude ?: 0.toDouble()
                flowOf(
                    getForecastInfoUseCase
                        .fetchForecastInfo(latitude, longitude)
                ).zip(
                    flowOf(
                        getCurrentWeatherInfoUseCase
                            .fetchCurrentWeatherInfo(latitude, longitude)
                    )
                ) { networkForecast, networkWeather ->
                    val forecast = networkForecast.getFetchedInfo()?.toForeCastUI()
                    val currentWeatherInfo = networkWeather
                        .getFetchedInfo()?.toCurrentWeatherInfoUI()
                    _viewStateFlow.value = viewStateFlow
                        .value.copy(
                            forecastInfo = forecast,
                            uiState = if (forecast != null && currentWeatherInfo != null)
                                UIState.REQUEST_CONTENT_COMPLETED
                            else UIState.ERROR,
                            currentWeatherInfo = currentWeatherInfo

                        )
                }.collect()
            }
            else{
                _viewStateFlow.value = viewStateFlow
                    .value.copy(uiState = UIState.GPS_ERROR)
            }
        }
    }
}