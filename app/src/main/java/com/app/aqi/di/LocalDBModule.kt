package com.app.aqi.di

import android.util.Log
import com.app.aqi.data.local.LocalDB
import com.app.aqi.data.local.RealmDBImp
import com.app.aqi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Provides
    @Singleton
    fun providesLocalDB(
    ): LocalDB {
        Log.v(Constants.TAG, "providesAPI ")
        return RealmDBImp()
    }

}