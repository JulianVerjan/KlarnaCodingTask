package com.test.weather

import com.test.model.CurrentWeatherInfo
import com.test.model.Forecast
import com.test.model.city.City
import com.test.model.clouds.Clouds
import com.test.model.coordinate.Coordinate
import com.test.model.sys.SysOnLocation
import com.test.model.weather.Weather
import com.test.model.weather.WeatherMainInformation
import com.test.model.weather.Wind

fun returnMockedCurrentWeatherInfo() = CurrentWeatherInfo(
    dt = 0,
    timezone = 1,
    id = 223,
    name = "Test Name",
    cod = 45,
    visibility = 9,
    base = "Info base",
    coord = Coordinate(23.6544, 34.55444),
    weather = listOf(Weather(
        8,
        "Main Info",
        "Test Description",
        "icon test"
    )),
    main = WeatherMainInformation(
        45.6,
        32,
        10,
        34,
        33,
        21,
        10,
        0,
        0.12F
    ),
    wind = Wind(
        speed = 23.65,
        deg = 56,
        gust = 54.9
    ),
    clouds = Clouds("All Test"),
    sys = SysOnLocation(
        id = 156,
        type = 0,
        country = "Berlin",
        sunrise = 7,
        sunset = 8
    )
)

fun returnMockedForecast() = Forecast(
    cod = "for",
    message = "Rain day",
    cnt = 2,
    city = City(
        id = 4,
        name = "Berlin",
        country = "Germany",
        population = 3333333,
        timezone = 1,
        sunrise = 2,
        sunset = 3,
        coord = Coordinate(23.6544, 34.55444)
    ),
    list = listOf()
)