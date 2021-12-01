package com.app.aqi.di

import com.app.aqi.data.local.LocalDB
import com.app.aqi.data.network.API
import com.app.aqi.data.reposistory.AQIDataRepositoryImp
import com.app.aqi.domain.repository.AQIDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesAQIRepository(
        apiImp: API,
        localDB: LocalDB
    ): AQIDataRepository {
        return AQIDataRepositoryImp(apiImp,localDB  )
    }
}