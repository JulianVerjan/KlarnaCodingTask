package com.test.weather.ui.weatherscreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.test.weather.BuildConfig
import com.test.weather.R
import com.test.weather.ui.weatherscreen.WeatherInfoState
import com.test.weather.ui.weatherscreen.composables.CollapsibleTopAppBarDefaults.bodyHeight
import com.test.weather.ui.weatherscreen.composables.CollapsibleTopAppBarDefaults.maxHeight
import com.test.weather.ui.weatherscreen.composables.CollapsibleTopAppBarDefaults.minHeight
import kotlin.math.min

@Composable
fun CollapsibleHeader(
    state: WeatherInfoState
) {
    CollapsibleTopAppBar(
        state = state,
        scrollOffset = LocalScrollOffset.current.value,
        insets = LocalInsets.current
    )
}

object CollapsibleTopAppBarDefaults {
    val minHeight = 60.dp
    val maxHeight = 80.dp
    val bodyHeight = maxHeight - minHeight
}

private val LocalScrollOffset = compositionLocalOf<State<Int>> {
    mutableStateOf(Int.MAX_VALUE)
}

private val LocalInsets = compositionLocalOf {
    PaddingValues(0.dp)
}

@Composable
private fun CollapsibleTopAppBar(
    state: WeatherInfoState,
    scrollOffset: Int,
    insets: PaddingValues
) {
    val density = LocalDensity.current
    val topBarHeight = remember { mutableStateOf(0) }
    val maxOffset = with(density) {
        bodyHeight.roundToPx() - insets.calculateTopPadding().roundToPx()
    }
    val startTranslateFraction = minHeight / maxHeight
    val offset = min(scrollOffset, maxOffset)
    val fraction = 1f - kotlin.math.max(0f, offset.toFloat()) / maxOffset

    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = startTranslateFraction > fraction,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row {
                    SubcomposeAsyncImage(
                        model = "${BuildConfig.IMAGE_URL}${state.currentWeatherInfo?.weather?.first()?.icon}@4x.png",
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = stringResource(R.string.app_name)
                    )
                    Text(
                        text = "${state.currentWeatherInfo?.main?.temp_max}°",
                        fontSize = 40.sp,
                        color = Color.White
                    )
                }
            }
        },
        backgroundColor = colorResource(R.color.main_screen_background),
        elevation =  0.dp,
        modifier = Modifier
            .onGloballyPositioned {
                topBarHeight.value = it.size.height
            }
    )

    BoxWithConstraints(
        modifier = Modifier
            .padding(top = 60.dp, start = 24.dp, end = 8.dp)
    ) {
        AnimatedVisibility(
            visible = fraction > startTranslateFraction,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CurrentWeatherCard(state)
        }
    }
}

@Composable
fun CollapsibleScaffold(
    state: ScrollState,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    content: @Composable (insets: PaddingValues) -> Unit
) {
    CollapsibleScaffoldInternal(
        offsetState = rememberOffsetScrollState(state),
        modifier = modifier,
        topBar = topBar,
        content = content
    )
}

@Composable
private fun CollapsibleScaffoldInternal(
    offsetState: State<Int>,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    content: @Composable (insets: PaddingValues) -> Unit
) {
    Scaffold(modifier = modifier, backgroundColor = Color.Transparent) { insets ->
        Box {
            content(
                PaddingValues(
                    top = maxHeight + 8.dp,
                    bottom = 16.dp
                )
            )
            CompositionLocalProvider(
                LocalScrollOffset provides offsetState,
                LocalInsets provides insets
            ) {
                topBar()
            }
        }
    }
}

@Composable
private fun rememberOffsetScrollState(state: ScrollState): MutableState<Int> {
    val offsetState = rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(key1 = state.value) {
        offsetState.value = state.value
    }
    return offsetState
}