package com.test.model.city

import com.test.model.coordinate.Coordinate

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int,
    val coord: Coordinate
)
