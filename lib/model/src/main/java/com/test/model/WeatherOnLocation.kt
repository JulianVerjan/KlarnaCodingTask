package com.test.model

import com.test.model.clouds.Clouds
import com.test.model.coordinate.Coordinate
import com.test.model.weather.Weather
import com.test.model.weather.WeatherMainInformation
import com.test.model.weather.Wind
import com.test.model.sys.SysOnLocation


data class WeatherOnLocation(
    val dt: Int,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
    val visibility: Int,
    val base: String,
    val coord: Coordinate,
    val weather: Weather,
    val main: WeatherMainInformation,
    val wind: Wind,
    val clouds: Clouds,
    val sys: SysOnLocation
)
