package com.test.network.model.mapper

import com.test.model.DaysInformation
import com.test.model.Forecast
import com.test.model.WeatherOnLocation
import com.test.network.model.reponse.DaysInformationResponse
import com.test.network.model.reponse.ForecastResponse
import com.test.network.model.reponse.WeatherOnLocationResponse
import okhttp3.internal.toImmutableList

fun ForecastResponse.toForecastModel() = Forecast(
    code = this.code,
    message = this.message,
    cnt = this.cnt,
    city = this.city.toCityModel(),
    list = this.list.toDaysInformationModel()
)

fun WeatherOnLocationResponse.toWeatherOnLocationModel() = WeatherOnLocation(
    dt = this.dt,
    timezone = this.timezone,
    id = this.id,
    name = this.name,
    cod = this.cod,
    visibility = this.visibility,
    base = this.base,
    coord = this.coord.toCoordinateModel(),
    weather = this.weather.toWeatherModel(),
    main = this.main.toWeatherMainInformationModel(),
    wind = this.wind.toWindModel(),
    clouds = this.clouds.toCloudModel(),
    sys = this.sys.toSysOnLocationModel()
)

fun List<DaysInformationResponse>.toDaysInformationModel(): List<DaysInformation> {
    val listResult = mutableListOf<DaysInformation>()
    this.forEach {
        listResult.add(
            DaysInformation(
                dt = it.dt,
                visibility = it.visibility,
                dt_txt = it.dt_txt,
                main = it.main.toWeatherMainInformationModel(),
                weather = it.weather.toWeatherModel(),
                clouds = it.clouds.toCloudModel(),
                wind = it.wind.toWindModel(),
                sys = it.sys.toSysModel(),
                pop = it.pop
            )
        )
    }
    return listResult.toImmutableList()
}
