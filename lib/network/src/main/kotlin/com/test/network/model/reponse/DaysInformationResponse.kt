package com.test.network.model.reponse

import com.squareup.moshi.Json
import com.test.network.model.reponse.sys.SysResponse
import com.test.network.model.reponse.clouds.CloudsResponse
import com.test.network.model.reponse.weather.WeatherMainInformationResponse
import com.test.network.model.reponse.weather.WeatherResponse
import com.test.network.model.reponse.weather.WindResponse

data class DaysInformationResponse(
    @field:Json(name = "dt") val dt: Int,
    @field:Json(name = "visibility") val visibility: Int,
    @field:Json(name = "pop") val pop: Double,
    @field:Json(name = "dt_txt") val dt_txt: String,
    @field:Json(name = "main") val main: WeatherMainInformationResponse,
    @field:Json(name = "weather") val weather: List<WeatherResponse>,
    @field:Json(name = "clouds") val clouds: CloudsResponse,
    @field:Json(name = "wind") val wind: WindResponse,
    @field:Json(name = "sys") val sys: SysResponse
)