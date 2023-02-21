package com.test.weather

sealed class ScreenRoute(val route: String) {
    object WeatherScreenRoute: ScreenRoute("weather_screen")
    object PermissionsScreenRoute: ScreenRoute("permissions_screen")
    object LocationErrorScreenRoute: ScreenRoute("location_error_screen")

}
