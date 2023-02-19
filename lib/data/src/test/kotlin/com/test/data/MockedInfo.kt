package com.test.data

import com.test.model.Forecast
import com.test.model.WeatherOnLocation
import com.test.model.city.City
import com.test.model.clouds.Clouds
import com.test.model.coordinate.Coordinate
import com.test.model.sys.SysOnLocation
import com.test.model.weather.Weather
import com.test.model.weather.WeatherMainInformation
import com.test.model.weather.Wind
import com.test.network.model.reponse.ForecastResponse
import com.test.network.model.reponse.WeatherOnLocationResponse
import com.test.network.model.reponse.city.CityResponse
import com.test.network.model.reponse.clouds.CloudsResponse
import com.test.network.model.reponse.coordinate.CoordinateResponse
import com.test.network.model.reponse.sys.SysOnLocationResponse
import com.test.network.model.reponse.weather.WeatherMainInformationResponse
import com.test.network.model.reponse.weather.WeatherResponse
import com.test.network.model.reponse.weather.WindResponse

fun returnMockedForecastResponse() = ForecastResponse(
    code = "for",
    message = "Rain day",
    cnt = 2,
    city = CityResponse(
        id = 4,
        name = "Berlin",
        country = "Germany",
        population = 3333333,
        timezone = 1,
        sunrise = 2,
        sunset = 3,
        coord = CoordinateResponse(23.6544, 34.55444)
    ),
    list = listOf()
)

fun returnMockedWeatherOnLocationResponse() = WeatherOnLocationResponse(
    dt = 0,
    timezone = 1,
    id = 223,
    name = "Test Name",
    cod = 45,
    visibility = 9,
    base = "Info base",
    coord = CoordinateResponse(23.6544, 34.55444),
    weather = WeatherResponse(
        8,
        "Main Info",
        "Test Description",
        "icon test"
    ),
    main = WeatherMainInformationResponse(
        45.6,
        32.5,
        10.4,
        34.4,
        33,
        21,
        10,
        0,
        0.12F
    ),
    wind = WindResponse(
        speed = 23.65,
        deg = 56,
        gust = 54.9
    ),
    clouds = CloudsResponse("All Test"),
    sys = SysOnLocationResponse(
        id = 156,
        type = 0,
        country = "Berlin",
        sunrise = 7,
        sunset = 8
    )
)

fun returnMockedWeatherOnLocation() = WeatherOnLocation(
    dt = 0,
    timezone = 1,
    id = 223,
    name = "Test Name",
    cod = 45,
    visibility = 9,
    base = "Info base",
    coord = Coordinate(23.6544, 34.55444),
    weather = Weather(
        8,
        "Main Info",
        "Test Description",
        "icon test"
    ),
    main = WeatherMainInformation(
        45.6,
        32.5,
        10.4,
        34.4,
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
    code = "for",
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