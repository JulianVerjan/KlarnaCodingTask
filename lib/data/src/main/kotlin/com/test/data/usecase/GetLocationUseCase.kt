package com.test.data.usecase

import com.test.data.repository.LocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun getCurrentLocation() = locationRepository.getCurrentLocation()
}
