package com.test.network.model.mapper

import com.test.model.city.City
import com.test.model.clouds.Clouds
import com.test.model.coordinate.Coordinate
import com.test.model.sys.Sys
import com.test.model.sys.SysOnLocation
import com.test.model.weather.Weather
import com.test.model.weather.WeatherMainInformation
import com.test.model.weather.Wind
import com.test.network.model.reponse.city.CityResponse
import com.test.network.model.reponse.clouds.CloudsResponse
import com.test.network.model.reponse.coordinate.CoordinateResponse
import com.test.network.model.reponse.sys.SysOnLocationResponse
import com.test.network.model.reponse.sys.SysResponse
import com.test.network.model.reponse.weather.WeatherMainInformationResponse
import com.test.network.model.reponse.weather.WeatherResponse
import com.test.network.model.reponse.weather.WindResponse

fun CloudsResponse.toCloudModel() = Clouds(
    all = this.all
)

fun SysResponse.toSysModel() = Sys(
    pod = this.pod
)

fun SysOnLocationResponse.toSysOnLocationModel() = SysOnLocation(
    id = this.id,
    type = this.type,
    country = this.country,
    sunrise = this.sunrise,
    sunset = this.sunset
)

fun WindResponse.toWindModel() = Wind(
    speed = this.speed,
    deg = this.deg,
    gust = this.gust
)

fun CoordinateResponse.toCoordinateModel() = Coordinate(
    lat = this.lat,
    lon = this.lon,
)

fun WeatherMainInformationResponse.toWeatherMainInformationModel() = WeatherMainInformation(
    temp = this.temp,
    feels_like = this.feels_like,
    temp_min = this.temp_min,
    temp_max = this.temp_max,
    pressure = this.pressure,
    sea_level = this.sea_level,
    grnd_level = this.grnd_level,
    humidity = this.humidity,
    temp_kf = this.temp_kf
)

fun WeatherResponse.toWeatherModel() = Weather(
    id = this.id,
    main = this.main,
    description = this.description,
    icon = this.icon
)

fun CityResponse.toCityModel() = City(
    id = this.id,
    name = this.name,
    country = this.country,
    population = this.population,
    timezone = this.timezone,
    sunrise = this.sunrise,
    sunset = this.sunset,
    coord = this.coord.toCoordinateModel(),
)