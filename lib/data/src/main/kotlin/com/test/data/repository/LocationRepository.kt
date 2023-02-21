package com.test.data.repository

import com.test.data.source.CurrentLocationDataSource
import com.test.model.coordinate.CurrentLocation
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationDataSource: CurrentLocationDataSource
) {

    suspend fun getCurrentLocation(): CurrentLocation? = locationDataSource.getCurrentLocation()
}
