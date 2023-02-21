package com.test.weather.ui.weatherscreen.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.weather.R

@Composable
fun CurrentWeatherSecondaryCard(data: String, @DrawableRes iconRes: Int) {

    Card(
        backgroundColor = colorResource(id = R.color.weather_screen_background),
        modifier = Modifier
            .height(180.dp)
            .width(200.dp)
            .padding(start = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(24.dp),
            content = {
                Image(
                    painterResource(iconRes),
                    "content description"
                )
                Text(
                    text = data,
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = colorResource(R.color.white))
                )
            }
        )
    }
}
