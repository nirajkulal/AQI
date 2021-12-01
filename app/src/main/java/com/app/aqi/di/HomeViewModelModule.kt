package com.app.aqi.di

import android.util.Log
import com.app.aqi.data.network.API
import com.app.aqi.data.network.OkHttpAPIImp
import com.app.aqi.data.reposistory.AQIDataRepositoryImp
import com.app.aqi.domain.repository.AQIDataRepository
import com.app.aqi.domain.usecases.FetchAQIUseCase
import com.app.aqi.domain.usecases.FetchCitiesUseCase
import com.app.aqi.domain.usecases.StopAQIUseCase
import com.app.aqi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeViewModelModule {

    @Provides
    fun providesStopAQIUseCase(
        aqiDataRepository: AQIDataRepository
    ): StopAQIUseCase {
        return StopAQIUseCase(aqiDataRepository)
    }

    @Provides
    fun providesFetchAQIUseCase(
        aqiDataRepository: AQIDataRepository
    ): FetchAQIUseCase {
        return FetchAQIUseCase(aqiDataRepository)
    }


    @Provides
    fun providesFetchCitiesUseCase(
        aqiDataRepository: AQIDataRepository
    ): FetchCitiesUseCase {
        return FetchCitiesUseCase(aqiDataRepository)
    }


}

