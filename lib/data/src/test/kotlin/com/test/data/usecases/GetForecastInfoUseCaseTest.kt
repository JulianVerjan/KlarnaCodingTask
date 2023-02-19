package com.test.data.usecases

import com.test.data.repository.WeatherRepository
import com.test.data.returnMockedForecast
import com.test.data.returnMockedForecastResponse
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetForecastInfoUseCase
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
class GetForecastInfoUseCaseTest {

    private val weatherApi: WeatherApi = mock()
    private val weatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)
    private val weatherRepository = WeatherRepository(weatherRemoteDataSource)
    private val getForecastInfoUseCase = GetForecastInfoUseCase(weatherRepository)
    private val mockedLatitude = 12.3455
    private val mockedLongitude = 34.2123
    private val mockedCodeError = 405

    @Test
    fun fetchForecastInfoSuccessfully() {
        runTest {
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedForecastResponse()))
            whenever(weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Success(returnMockedForecast()))
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.Success(returnMockedForecastResponse()))

            val response = getForecastInfoUseCase.fetchForecastInfo(mockedLatitude, mockedLongitude)
            assert(response is NetworkResult.Success)
            val successResponse = response as NetworkResult.Success
            Assert.assertEquals(successResponse.result?.cnt, 2)
        }
    }

    @Test
    fun fetchForecastInfoWithApiError() {
        runTest {
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.ApiError(returnMockedForecast(), mockedCodeError))
            whenever(weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Fail(returnMockedForecast()))
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.ApiError(returnMockedForecast(), mockedCodeError))

            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Fail<*>)
        }
    }

    @Test
    fun fetchForecastInfoNetworkError() {
        runTest {
            whenever(weatherApi.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            whenever(weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResult.Exception(IOException()))
            whenever(weatherRemoteDataSource.fetchForecastInfo(mockedLatitude, mockedLongitude))
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            val response = weatherRepository.fetchForecastInfo(mockedLatitude, mockedLongitude)
            Assert.assertNotNull(response)
            assert(response is NetworkResult.Exception)
        }
    }
}