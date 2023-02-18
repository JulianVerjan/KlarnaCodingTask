package com.test.network.model.reponse

import com.squareup.moshi.Json
import com.test.network.model.reponse.clouds.CloudsResponse
import com.test.network.model.reponse.coordinate.CoordinateResponse
import com.test.network.model.reponse.sys.SysOnLocationResponse
import com.test.network.model.reponse.weather.WeatherMainInformationResponse
import com.test.network.model.reponse.weather.WindResponse
import com.test.network.model.reponse.weather.WeatherResponse

data class WeatherOnLocationResponse(
    @field:Json(name = "dt") val dt: Int,
    @field:Json(name = "timezone") val timezone: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "cod") val cod: Int,
    @field:Json(name = "visibility") val visibility: Int,
    @field:Json(name = "base") val base: String,
    @field:Json(name = "coord") val coord: CoordinateResponse,
    @field:Json(name = "weather") val weather: WeatherResponse,
    @field:Json(name = "main") val main: WeatherMainInformationResponse,
    @field:Json(name = "wind") val wind: WindResponse,
    @field:Json(name = "clouds") val clouds: CloudsResponse,
    @field:Json(name = "sys") val sys: SysOnLocationResponse
)
