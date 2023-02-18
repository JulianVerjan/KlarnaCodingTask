package com.test.data

import com.test.data.repository.WeatherRepository
import com.test.data.source.WeatherRemoteDataSource
import com.test.data.usecase.GetForecastInfoUseCase
import com.test.data.usecase.GetWeatherOnLocationInfoUseCase
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
    fun provideGetWeatherOnLocationInfoUseCase(
        weatherRepository: WeatherRepository
    ): GetWeatherOnLocationInfoUseCase {
        return GetWeatherOnLocationInfoUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideGetForecastInfoUseCase(
        weatherRepository: WeatherRepository
    ): GetForecastInfoUseCase {
        return GetForecastInfoUseCase(weatherRepository)
    }
}