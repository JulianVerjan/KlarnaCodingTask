package com.test.data.usecases

import com.test.data.repository.WeatherRepository
import com.test.data.returnMockedWeatherOnLocation
import com.test.data.returnMockedWeatherOnLocationResponse
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetWeatherOnLocationInfoUseCase
import com.test.network.model.NetworkResponse
import com.test.network.model.mapper.NetworkResult
import com.test.network.service.WeatherApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class GetWeatherOnLocationInfoUseCaseTest {

    private val weatherApi: WeatherApi = mock()
    private val weatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)
    private val weatherRepository = WeatherRepository(weatherRemoteDataSource)
    private val getWeatherOnLocationInfoUseCase = GetWeatherOnLocationInfoUseCase(weatherRepository)
    private val mockedLatitude = 12.3455
    private val mockedLongitude = 34.2123
    private val mockedCodeError = 405

    @Test
    fun fetchWeatherOnLocationInfoSuccessfully() {
        runTest {
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedWeatherOnLocationResponse()))
            whenever(weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Success(returnMockedWeatherOnLocation()))
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(NetworkResponse.Success(returnMockedWeatherOnLocationResponse()))

            val response = getWeatherOnLocationInfoUseCase.fetchWeatherOnLocationInfo(
                mockedLatitude, mockedLongitude
            )
            assert(response is NetworkResult.Success)
            val successResponse = response as NetworkResult.Success
            Assert.assertEquals(successResponse.result?.id, 223)
        }
    }

    @Test
    fun fetchWeatherOnLocationInfoWithApiError() {
        runTest {
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(
                    NetworkResponse.ApiError(
                        returnMockedWeatherOnLocation(),
                        mockedCodeError
                    )
                )
            whenever(weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Fail(returnMockedWeatherOnLocation()))
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(
                NetworkResponse.ApiError(
                    returnMockedWeatherOnLocation(),
                    mockedCodeError
                )
            )

            val response =
                weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Fail<*>)
        }
    }

    @Test
    fun fetchWeatherOnLocationInfoWithNetworkError() {
        runTest {
            whenever(weatherApi.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            whenever(weatherRepository.fetchWeatherOnLocationInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Exception(IOException()))
            whenever(
                weatherRemoteDataSource.fetchWeatherOnLocationInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(NetworkResponse.NetworkError(IOException()))

            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Exception)
        }
    }
}