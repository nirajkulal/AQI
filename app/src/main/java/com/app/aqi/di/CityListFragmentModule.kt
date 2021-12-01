package com.app.aqi.di

import com.app.aqi.application.view.CityListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object CityListFragmentModule {

    @Provides
    fun providesAdapter(
    ): CityListAdapter {
        return CityListAdapter()
    }
}