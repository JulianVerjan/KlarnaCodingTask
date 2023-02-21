package com.test.data.usecases

import com.test.data.repository.WeatherRepository
import com.test.data.returnMockedCurrentWeatherInfo
import com.test.data.returnMockedCurrentWeatherInfoResponse
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetCurrentWeatherInfoUseCase
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
class GetCurrentWeatherInfoUseCaseTest {

    private val weatherApi: WeatherApi = mock()
    private val weatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)
    private val weatherRepository = WeatherRepository(weatherRemoteDataSource)
    private val getCurrentWeatherInfoUseCase = GetCurrentWeatherInfoUseCase(weatherRepository)
    private val mockedLatitude = 12.3455
    private val mockedLongitude = 34.2123
    private val mockedCodeError = 405

    @Test
    fun fetchCurrentWeatherInfoSuccessfully() {
        runTest {
            whenever(weatherApi.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedCurrentWeatherInfoResponse()))
            whenever(weatherRepository.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Success(returnMockedCurrentWeatherInfo()))
            whenever(
                weatherRemoteDataSource.fetchCurrentWeatherInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(NetworkResponse.Success(returnMockedCurrentWeatherInfoResponse()))

            val response = getCurrentWeatherInfoUseCase.fetchCurrentWeatherInfo(
                mockedLatitude, mockedLongitude
            )
            assert(response is NetworkResult.Success)
            val successResponse = response as NetworkResult.Success
            Assert.assertEquals(successResponse.result?.id, 223)
        }
    }

    @Test
    fun fetchCurrentWeatherInfoWithApiError() {
        runTest {
            whenever(weatherApi.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(
                    NetworkResponse.ApiError(
                        returnMockedCurrentWeatherInfo(),
                        mockedCodeError
                    )
                )
            whenever(weatherRepository.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Fail(returnMockedCurrentWeatherInfo()))
            whenever(
                weatherRemoteDataSource.fetchCurrentWeatherInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(
                NetworkResponse.ApiError(
                    returnMockedCurrentWeatherInfo(),
                    mockedCodeError
                )
            )

            val response =
                weatherRepository.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Fail<*>)
        }
    }

    @Test
    fun fetchCurrentWeatherInfoWithNetworkError() {
        runTest {
            whenever(weatherApi.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            whenever(weatherRepository.fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Exception(IOException()))
            whenever(
                weatherRemoteDataSource.fetchCurrentWeatherInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            ).thenReturn(NetworkResponse.NetworkError(IOException()))

            val response = weatherRepository
                .fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Exception)
        }
    }
}