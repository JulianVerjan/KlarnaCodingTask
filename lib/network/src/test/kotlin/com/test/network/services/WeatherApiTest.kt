package com.test.network.services

import com.test.network.BuildConfig
import com.test.network.adapter.NetworkResponseAdapterFactory
import com.test.network.model.mapper.toRepositoryResult
import com.test.network.service.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val FORECAST_STATUS_200 = "mock-responses/get-forecast-status200.json"
const val WEATHER_BY_DAY_STATUS_200 = "mock-responses/get-weather-per-day-status200.json"
const val STATUS_401 = "mock-responses/get-weather-per-day-status200.json"

@ExperimentalCoroutinesApi
class WeatherApiTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        weatherApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.API_BASE_URL))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }

    @Test
    fun responseFetchForecastSuccessfully() {
        enqueueResponse(FORECAST_STATUS_200)
        runTest {
            val response =
                weatherApi.fetchForecastInfo(12.334, 11.2344)
            assertEquals(
                200,
                response.toRepositoryResult { it.code }
            )
        }
    }

    @Test
    fun responseFetchWeatherOnLocationInfoSuccessfully() {
        enqueueResponse(WEATHER_BY_DAY_STATUS_200)
        runTest {
            val response =
                weatherApi.fetchWeatherOnLocationInfo(12.334, 11.2344)
            assertEquals(
                6545310,
                response.toRepositoryResult { it.id }
            )
        }
    }

    @Test
    fun responseGetErrorResponseFromForecastService() {
        enqueueResponse(STATUS_401)
        runTest {
            val response =
                weatherApi.fetchForecastInfo(12.334, 11.2344)
            assertEquals(400, response)
            assertEquals(false, response.toRepositoryResult { it.code })
            assertNull(response)
        }
    }

    private fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource!!.readString(Charsets.UTF_8)
            )
        )
    }
}
