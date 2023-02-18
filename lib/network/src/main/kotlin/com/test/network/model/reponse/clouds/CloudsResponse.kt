package com.test.network.model.reponse.clouds

import com.squareup.moshi.Json

data class CloudsResponse(
    @field:Json(name = "all") val all: String
)
