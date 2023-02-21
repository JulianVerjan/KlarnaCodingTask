package com.test.weather.ui.permissionscreen

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.test.weather.ScreenRoute
import com.test.weather.ui.permissionscreen.composables.PermissionNotGrantedMessage
import com.test.weather.ui.permissionscreen.composables.WaitForPermissionMessage

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(
    navController: NavController
) {
    var doNotShowMeRationale by rememberSaveable {
        mutableStateOf(false)
    }
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    PermissionsRequired(
        multiplePermissionsState = multiplePermissionState,
        permissionsNotGrantedContent = {
            if (doNotShowMeRationale) {
                WaitForPermissionMessage(onYesClick = {
                    multiplePermissionState
                        .launchMultiplePermissionRequest()
                }, onCancelClick = { navController.popBackStack() })

            } else {
                PermissionNotGrantedMessage(
                    onYesClick = { multiplePermissionState.launchMultiplePermissionRequest() },
                    onCancelClick = { doNotShowMeRationale = true })
            }
        },
        permissionsNotAvailableContent = { /* ... */ },
        content = {
            LaunchedEffect(Unit) {
                navController.navigate(ScreenRoute.WeatherScreenRoute.route) {
                    popUpTo(ScreenRoute.PermissionsScreenRoute.route) { inclusive = true }
                }
            }
        }
    )
}