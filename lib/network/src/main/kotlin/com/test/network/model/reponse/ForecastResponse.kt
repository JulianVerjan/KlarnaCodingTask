package com.test.network.model.reponse

import com.squareup.moshi.Json
import com.test.network.model.reponse.city.CityResponse

data class ForecastResponse(
    @field:Json(name = "cod") val cod: String,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "cnt") val cnt: Int,
    @field:Json(name = "city") val city: CityResponse,
    @field:Json(name = "list") val list: List<DaysInformationResponse>
)