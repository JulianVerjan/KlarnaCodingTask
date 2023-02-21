package com.test.weather.ui.weatherscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.weather.R
import com.test.weather.ui.weatherscreen.composables.LocationErrorScreen
import com.test.weather.ui.weatherscreen.composables.CollapsibleWeatherHeader
import com.test.weather.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state = viewModel.viewStateFlow.collectAsState(initial = WeatherInfoState())
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = colorResource(id = R.color.main_screen_background)
    ) {
        when (state.value.uiState) {
            UIState.REQUEST_CONTENT_COMPLETED -> {
                CollapsibleWeatherHeader(
                    state.value,
                )
            }
            UIState.GPS_ERROR -> {
                LocationErrorScreen()
            }
            UIState.IDLE,
            UIState.LOADING,
            UIState.ERROR -> {
            }
        }
    }
}