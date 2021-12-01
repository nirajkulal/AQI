package com.app.aqi.di

import android.util.Log
import com.app.aqi.data.local.LocalDB
import com.app.aqi.data.network.API
import com.app.aqi.data.network.OkHttpAPIImp
import com.app.aqi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
    ): OkHttpClient {
        Log.v(Constants.TAG, "providesOkHttpClient ")
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun providesRequest(
    ): Request {
        Log.v(Constants.TAG, "providesRequest ")
        return Request.Builder().url(Constants.API_URL).build()
    }

    @Provides
    @Singleton
    fun providesAPI(
        client: OkHttpClient,
        request: Request,
        localDB: LocalDB
    ): API {
        Log.v(Constants.TAG, "providesAPI ")
        return OkHttpAPIImp(client, request, localDB)
    }

}