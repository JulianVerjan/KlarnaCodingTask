package com.test.weather.ui.weatherscreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.test.weather.BuildConfig
import com.test.weather.R
import com.test.weather.ui.weatherscreen.WeatherInfoState

@Composable
fun CurrentWeatherHeader(
    state: WeatherInfoState
) {
    state.let { data ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = "${BuildConfig.IMAGE_URL}${data.currentWeatherInfo?.weather?.first()?.icon}@4x.png",
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = stringResource(R.string.app_name)
            )
            Text(
                text = "${data.currentWeatherInfo?.main?.temp_max}°",
                fontSize = 50.sp,
                color = Color.White
            )
            Text(
                text = "${data.currentWeatherInfo?.name}",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CurrentWeatherInfoUI(
                    value = data.currentWeatherInfo?.main?.pressure ?: 0,
                    unit = "hpa",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                CurrentWeatherInfoUI(
                    value = data.currentWeatherInfo?.main?.humidity ?: 0,
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                CurrentWeatherInfoUI(
                    value = data.currentWeatherInfo?.wind?.speed?.toInt() ?: 0,
                    unit = "km/h",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
            }
        }
    }
}