package com.test.network.model.reponse.coordinate

import com.squareup.moshi.Json

data class CoordinateResponse(
    @field:Json(name = "lat") val lat: Double,
    @field:Json(name = "lon") val lon: Double
)
