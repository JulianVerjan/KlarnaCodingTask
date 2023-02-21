package com.test.weather.ui.weatherscreen.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.weather.R
import com.test.weather.ui.weatherscreen.WeatherInfoState

@Composable
fun CollapsibleWeatherHeader(
    state: WeatherInfoState
) {
    val scrollState = rememberScrollState()
    CollapsibleScaffold(
        state = scrollState,
        topBar = { CollapsibleHeader(state) }
    ) { insets ->
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(
                modifier = Modifier.padding(
                    start = insets.calculateStartPadding(LayoutDirection.Ltr),
                    end = insets.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = insets.calculateBottomPadding(),
                    top = insets.calculateTopPadding()
                )
            )
            Spacer(Modifier.size(180.dp))
            Text(
                text = stringResource(R.string.weather_next_days_title),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.white)
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(Modifier.size(8.dp))
            ForecastHorizontalScrollList(state.forecastInfo?.list)
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.weather_sunrise_title),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.white)
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            SunsetAndSunriseCard(
                state.forecastInfo?.city
                    ?.sunrise?.toLong() ?: 0L,
                R.drawable.ic_sunrise
            )

            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.weather_sunset_title),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.white)
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            SunsetAndSunriseCard(
                state.forecastInfo?.city
                    ?.sunset?.toLong() ?: 0L,
                R.drawable.ic_sunset
            )
        }
    }
}
