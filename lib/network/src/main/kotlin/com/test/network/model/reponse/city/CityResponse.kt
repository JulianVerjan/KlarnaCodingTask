package com.test.network.model.reponse.city

import com.squareup.moshi.Json
import com.test.network.model.reponse.coordinate.CoordinateResponse

data class CityResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "population") val population: Int,
    @field:Json(name = "timezone") val timezone: Int,
    @field:Json(name = "sunrise") val sunrise: Int,
    @field:Json(name = "sunset") val sunset: Int,
    @field:Json(name = "coord") val coord: CoordinateResponse
)
