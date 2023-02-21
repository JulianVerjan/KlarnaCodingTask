package com.test.weather.mapper

import com.test.model.Forecast
import com.test.model.CurrentWeatherInfo
import com.test.network.model.mapper.NetworkResult

fun NetworkResult<Any>.getFetchedInfo(): Any? {
    return when (this) {
        is NetworkResult.Success -> {
            this.result
        }
        else -> {
            null
        }
    }
}

fun Any.toForeCastUI(): Forecast? {
    return if (this is Forecast) {
        this
    } else {
        null
    }
}

fun Any.toCurrentWeatherInfoUI(): CurrentWeatherInfo? {
    return if (this is CurrentWeatherInfo) {
        this
    } else {
        null
    }
}