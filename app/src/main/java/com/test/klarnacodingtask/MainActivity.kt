package com.test.klarnacodingtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.klarnacodingtask.theme.KlarnaCodingTaskTheme
import com.test.weather.ScreenRoute
import com.test.weather.ui.weatherscreen.WeatherScreen
import com.test.weather.ui.permissionscreen.PermissionsScreen
import com.test.weather.ui.weatherscreen.composables.LocationErrorScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KlarnaCodingTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.PermissionsScreenRoute.route
                    ) {
                        composable(route = ScreenRoute.PermissionsScreenRoute.route)
                        { PermissionsScreen(navController) }

                        composable(route = ScreenRoute.WeatherScreenRoute.route)
                        { WeatherScreen(navController) }

                        composable(route = ScreenRoute.LocationErrorScreenRoute.route)
                        { LocationErrorScreen() }
                    }
                }
            }
        }
    }
}