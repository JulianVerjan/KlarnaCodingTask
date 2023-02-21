package com.test.weather

import com.test.data.repository.LocationRepository
import com.test.data.repository.WeatherRepository
import com.test.data.source.CurrentLocationDataSource
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetForecastInfoUseCase
import com.test.data.usecase.GetLocationUseCase
import com.test.data.usecase.GetCurrentWeatherInfoUseCase
import com.test.model.coordinate.CurrentLocation
import com.test.network.model.mapper.NetworkResult
import com.test.network.service.WeatherApi
import com.test.weather.ui.weatherscreen.UIState
import com.test.weather.viewmodel.WeatherViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private val weatherApi: WeatherApi = mockk(relaxed = true)
    private val currentLocationDataSource: CurrentLocationDataSource = mockk(relaxed = true)
    private val locationRepository = LocationRepository(currentLocationDataSource)
    private val weatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)
    private val weatherRepository = WeatherRepository(weatherRemoteDataSource)
    private val getCurrentWeatherInfoUseCase = GetCurrentWeatherInfoUseCase(weatherRepository)

    private val getForecastInfoUseCase = GetForecastInfoUseCase(weatherRepository)
    private val getLocationUseCase = GetLocationUseCase(locationRepository)


    private var weatherMonitorViewModel =
        WeatherViewModel(
            getCurrentWeatherInfoUseCase,
            getForecastInfoUseCase,
            getLocationUseCase
        )

    private val mockedLatitude = 12.3455
    private val mockedLongitude = 34.2123
    private val mockedCurrentLocation = CurrentLocation(mockedLatitude, mockedLongitude)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchWeatherInfoSuccessfully() {
        runBlocking {

            coEvery { getLocationUseCase.getCurrentLocation() } returns mockedCurrentLocation
            coEvery {
                getForecastInfoUseCase.fetchForecastInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            } returns NetworkResult.Success(returnMockedForecast())

            coEvery {
                getCurrentWeatherInfoUseCase
                    .fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude)
            } returns NetworkResult.Success(returnMockedCurrentWeatherInfo())

            weatherMonitorViewModel.fetchWeatherInfo()
            val job = launch {
                weatherMonitorViewModel.viewStateFlow.collect {
                    assertEquals(it.uiState, UIState.REQUEST_CONTENT_COMPLETED)
                    assertEquals(it.currentWeatherInfo, returnMockedCurrentWeatherInfo())
                    assertEquals(it.forecastInfo, returnMockedForecast())
                }
            }
            job.cancel()
        }
    }


    @Test
    fun fetchWeatherInfoWithFail() {
        runBlocking {
            coEvery { getLocationUseCase.getCurrentLocation() } returns mockedCurrentLocation
            coEvery {
                getForecastInfoUseCase.fetchForecastInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            } returns NetworkResult.Fail("Error")

            coEvery {
                getCurrentWeatherInfoUseCase
                    .fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude)
            } returns NetworkResult.Fail("Error")

            weatherMonitorViewModel.fetchWeatherInfo()
            val job = launch {
                weatherMonitorViewModel.viewStateFlow.collect {
                    assertEquals(it.uiState, UIState.ERROR)
                    assertEquals(it.currentWeatherInfo, null)
                    assertEquals(it.forecastInfo, null)
                }
            }
            job.cancel()
        }
    }

    @Test
    fun fetchWeatherInfoWithApiError() {
        runBlocking {
            coEvery { getLocationUseCase.getCurrentLocation() } returns mockedCurrentLocation
            coEvery {
                getForecastInfoUseCase.fetchForecastInfo(
                    mockedLatitude,
                    mockedLongitude
                )
            } returns NetworkResult.Exception(Exception())

            coEvery {
                getCurrentWeatherInfoUseCase
                    .fetchCurrentWeatherInfo(mockedLatitude, mockedLongitude)
            } returns NetworkResult.Exception(Exception())

            weatherMonitorViewModel.fetchWeatherInfo()
            val job = launch {
                weatherMonitorViewModel.viewStateFlow.collect {
                    assertEquals(it.uiState, UIState.ERROR)
                    assertEquals(it.currentWeatherInfo, null)
                    assertEquals(it.forecastInfo, null)
                }
            }
            job.cancel()
        }
    }
}
