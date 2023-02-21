package com.test.network.location

import com.test.model.coordinate.CurrentLocation

interface CurrentLocationApi {
    suspend fun getCurrentLocation(): CurrentLocation?
}