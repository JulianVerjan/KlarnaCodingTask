package com.test.weather.ui.weatherscreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.test.model.DaysInformation
import com.test.weather.BuildConfig
import com.test.weather.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


const val HOUR_PATTERN = "H:mm"
const val DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

@Composable
fun ForecastHorizontalScrollList(list: List<DaysInformation>?) {
    list?.let {
        LazyRow(content = {
            itemsIndexed(it) { _, weatherData ->
                val formatHour = getFormatHour(weatherData.dt_txt)
                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    SubcomposeAsyncImage(
                        model = "${BuildConfig.IMAGE_URL}${weatherData.weather.first().icon}@4x.png",
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = stringResource(R.string.app_name)
                    )
                    Text(
                        text = "${weatherData.main.temp_max}°C",
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatHour,
                        color = colorResource(id = R.color.white),
                    )
                }
            }
        })
    }
}

private fun getFormatHour(date: String): String {
    val dateTime: LocalDateTime =
        LocalDateTime.parse(
            date,
            DateTimeFormatter.ofPattern(DATE_PATTERN)
        )
    return DateTimeFormatter.ofPattern(HOUR_PATTERN).format(dateTime)
}
