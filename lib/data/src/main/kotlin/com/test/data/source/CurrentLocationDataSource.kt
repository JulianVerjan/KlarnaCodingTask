package com.test.data.source

import android.annotation.SuppressLint
import android.app.Application
import com.google.android.gms.location.LocationServices
import com.test.model.coordinate.CurrentLocation
import com.test.network.location.CurrentLocationApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class CurrentLocationDataSource @Inject constructor(
    appContext: Application
) : CurrentLocationApi {

    private var locationClient = LocationServices.getFusedLocationProviderClient(appContext)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): CurrentLocation? {
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) cont.resume(
                        CurrentLocation(
                            result.latitude,
                            result.longitude
                        )
                    )
                    else cont.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(CurrentLocation(it?.latitude, it?.longitude))
                }
                addOnFailureListener {
                    cont.cancel(it.cause)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}