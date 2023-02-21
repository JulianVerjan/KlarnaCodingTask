package com.test.model

import com.test.model.clouds.Clouds
import com.test.model.weather.Weather
import com.test.model.weather.WeatherMainInformation
import com.test.model.weather.Wind
import com.test.model.sys.Sys

data class DaysInformation(
    val dt: Int,
    val visibility: Int,
    val pop: Double,
    val dt_txt: String,
    val main: WeatherMainInformation,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val sys: Sys
)