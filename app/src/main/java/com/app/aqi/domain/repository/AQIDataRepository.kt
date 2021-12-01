package com.app.aqi.domain.repository

import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.pojo.City
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface AQIDataRepository {

    fun connectWH()

    fun closeWH()

    fun fetchCities() : Single<ArrayList<Cities>>

    fun fetchCiy(name: String) : Single<City>

}