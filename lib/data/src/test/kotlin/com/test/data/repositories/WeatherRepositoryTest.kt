package com.test.data.repositories

import com.test.data.repository.WeatherRepository
import com.test.data.returnMockedForecast
import com.test.data.returnMockedForecastResponse
import com.test.data.returnMockedWeatherOnLocation
import com.test.data.returnMockedWeatherOnLocationResponse
import com.test.data.source.WeatherRemoteDataSource
import com.test.network.model.NetworkResponse
import com.test.network.model.mapper.NetworkResult
import com.test.network.service.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    private val weatherApi: WeatherApi = mock()
    private val weatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)
    private val weatherRepository = WeatherRepository(weatherRemoteDataSource)
    private val mockedLatitude = 12.3455
    private val mockedLongitude = 34.2123
    private val mockedCodeError = 405

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchForecastInfoSuccessfully() {
        runTest {
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedForecastResponse()))
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedForecastResponse()))

            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            assert(response is NetworkResult.Success)
            val successResponse = response as NetworkResult.Success
            assertEquals(successResponse.result?.cnt, 2)
        }
    }

    @Test
    fun fetchForecastInfoWithApiError() {
        runTest {
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.ApiError(returnMockedForecast(), mockedCodeError))
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.ApiError(returnMockedForecast(), mockedCodeError))

            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            assertNotNull(response)
            assert(response is NetworkResult.Fail<*>)
        }
    }

    @Test
    fun fetchForecastInfoNetworkError() {
        runTest {
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            assertNotNull(response)
            assert(response is NetworkResult.Exception)
        }
    }

    @Test
    fun fetchWeatherOnLocationInfoSuccessfully() {
        runTest {
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            )
                .thenReturn(NetworkResponse.Success(returnMockedWeatherOnLocationResponse()))
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedWeatherOnLocationResponse()))

            val response =
                weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude)
            assert(response is NetworkResult.Success)
            val successResponse = response as NetworkResult.Success
            assertEquals(successResponse.result?.id, 223)
        }
    }

    @Test
    fun fetchWeatherOnLocationInfoWithApiError() {
        runTest {
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            )
                .thenReturn(
                    NetworkResponse.ApiError(
                        returnMockedWeatherOnLocation(),
                        mockedCodeError
                    )
                )
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(
                    NetworkResponse.ApiError(
                        returnMockedWeatherOnLocation(),
                        mockedCodeError
                    )
                )

            val response =
                weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude)
            assertNotNull(response)
            assert(response is NetworkResult.Fail<*>)
        }
    }

    @Test
    fun fetchWeatherOnLocationInfoWithNetworkError() {
        runTest {
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            )
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            val response =
                weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude)
            assertNotNull(response)
            assert(response is NetworkResult.Exception)
        }
    }
}
