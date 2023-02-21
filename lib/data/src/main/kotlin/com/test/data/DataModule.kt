package com.test.data

import android.app.Application
import com.test.data.repository.LocationRepository
import com.test.data.repository.WeatherRepository
import com.test.data.source.CurrentLocationDataSource
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetForecastInfoUseCase
import com.test.data.usecase.GetLocationUseCase
import com.test.data.usecase.GetCurrentWeatherInfoUseCase
import com.test.network.service.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // / Provide Remote Data Source ///

    @Singleton
    @Provides
    fun provideWeatherDataSource(
        weatherApi: WeatherApi
    ): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(weatherApi)
    }
    @Provides
    @Singleton
    fun provideCurrentLocationDataSource(
        appContext: Application
    ): CurrentLocationDataSource {
        return CurrentLocationDataSource(appContext)
    }

    // / Provide repositories  and use cases///

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherRemoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository {
        return WeatherRepository(weatherRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(
        locationDataSource: CurrentLocationDataSource
    ): LocationRepository {
        return LocationRepository(locationDataSource)
    }

    @Singleton
    @Provides
    fun provideGetLocationUseCase(
        locationRepository: LocationRepository
    ): GetLocationUseCase {
        return GetLocationUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideGetCurrentWeatherInfoUseCase(
        weatherRepository: WeatherRepository
    ): GetCurrentWeatherInfoUseCase {
        return GetCurrentWeatherInfoUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideGetForecastInfoUseCase(
        weatherRepository: WeatherRepository
    ): GetForecastInfoUseCase {
        return GetForecastInfoUseCase(weatherRepository)
    }
}