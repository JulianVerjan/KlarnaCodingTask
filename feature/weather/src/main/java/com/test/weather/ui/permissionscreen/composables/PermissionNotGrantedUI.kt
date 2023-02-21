package com.test.weather.ui.permissionscreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.weather.R

@Composable
fun PermissionNotGrantedUI(
    onYesClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(colorResource(R.color.main_screen_background))
            .fillMaxSize()
            .padding(32.dp)
    ) {

        Text(
            text = stringResource(R.string.location_permission_title),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.location_permission_message),
            textAlign = TextAlign.Center,
            color = colorResource(R.color.white)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onYesClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    colorResource(R.color.main_screen_color_button_ok)
                )

            ) {
                Text(
                    text = stringResource(R.string.location_permission_button_ok),
                    color = colorResource(R.color.white)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onCancelClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    colorResource(R.color.main_screen_color_button_cancel)
                )
            ) {
                Text(
                    text = stringResource(R.string.location_permission_button_cancel),
                    color = colorResource(R.color.white)
                )
            }
        }
    }
}