package com.test.network.mappers

import com.test.network.model.mapper.toForecastModel
import com.test.network.model.mapper.toWeatherOnLocationModel
import org.junit.Assert
import org.junit.Test

class WeatherApiResponseMappersTest {
    @Test
    fun mapForecastInfoSuccessfully() {
        val mockedInfo = returnMockedForecastResponse().toForecastModel()
        Assert.assertEquals(returnMockedForecast(), mockedInfo)
    }

    @Test
    fun mapWeatherOnLocationResponseSuccessfully() {
        val mockedInfo = returnMockedWeatherOnLocationResponse().toWeatherOnLocationModel()
        Assert.assertEquals(returnMockedWeatherOnLocation(), mockedInfo)
    }
}