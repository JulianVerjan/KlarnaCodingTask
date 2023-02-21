package com.test.model.weather

data class WeatherMainInformation(
    val temp: Double,
    val feels_like: Int,
    val temp_min: Int,
    val temp_max: Int,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Float
)