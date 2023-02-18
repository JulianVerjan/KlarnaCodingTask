package com.test.network.model.reponse.sys

import com.squareup.moshi.Json

data class SysResponse(
    @field:Json(name = "pod") val pod: String
)
