package com.test.weather.ui.weatherscreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.test.model.DaysInformation
import com.test.weather.BuildConfig
import com.test.weather.R

@Composable
fun ForecastHorizontalScrollList(list: List<DaysInformation>?) {
    list?.let {
        Box(
            modifier = Modifier.padding(8.dp),
        ) {
            BoxWithConstraints {
                LazyRow(
                    state = rememberLazyListState(),
                ) {
                    itemsIndexed(it) { _, item ->
                        Card(
                            backgroundColor = colorResource(id = R.color.weather_screen_background),
                            modifier = Modifier
                                .height(180.dp)
                                .width(200.dp)
                                .padding(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(24.dp),
                                content = {
                                    SubcomposeAsyncImage(
                                        model = "${BuildConfig.IMAGE_URL}${item.weather.first().icon}@4x.png",
                                        loading = {
                                            CircularProgressIndicator()
                                        },
                                        contentDescription = stringResource(R.string.app_name)
                                    )
                                    Text(
                                        text = item.dt_txt,
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(color = colorResource(R.color.white))
                                    )
                                    Text(
                                        text = item.weather.first().description,
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(color = colorResource(R.color.white))
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
