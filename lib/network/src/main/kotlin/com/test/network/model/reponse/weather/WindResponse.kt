package com.test.network.model.reponse.weather

import com.squareup.moshi.Json

data class WindResponse(
    @field:Json(name = "speed") val speed: Double,
    @field:Json(name = "deg") val deg: Int,
    @field:Json(name = "gust") val gust: Double?
)
