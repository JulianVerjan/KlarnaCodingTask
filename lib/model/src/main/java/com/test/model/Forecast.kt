package com.test.model

import com.test.model.city.City

data class Forecast(
    val cod: String,
    val message: String,
    val cnt: Int,
    val city: City,
    val list: List<DaysInformation>
)