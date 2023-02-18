package com.test.network.model.reponse.sys

import com.squareup.moshi.Json

data class SysOnLocationResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "type") val type: Int,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "sunrise") val sunrise: Int,
    @field:Json(name = "sunset") val sunset: Int
)
