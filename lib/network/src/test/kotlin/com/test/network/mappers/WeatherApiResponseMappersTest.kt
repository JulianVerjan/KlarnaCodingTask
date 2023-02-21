package com.test.network.mappers

import com.test.network.model.mapper.toForecastModel
import com.test.network.model.mapper.toCurrentWeatherInfoModel
import org.junit.Assert
import org.junit.Test

class WeatherApiResponseMappersTest {
    @Test
    fun mapForecastInfoSuccessfully() {
        val mockedInfo = returnMockedForecastResponse().toForecastModel()
        Assert.assertEquals(returnMockedForecast(), mockedInfo)
    }

    @Test
    fun mapCurrentWeatherInfoSuccessfully() {
        val mockedInfo = returnMockedCurrentWeatherInfoResponse().toCurrentWeatherInfoModel()
        Assert.assertEquals(returnMockedCurrentWeatherInfo(), mockedInfo)
    }
}