package com.app.aqi.data.local

import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.pojo.City

interface LocalDB {

    fun getCities(): ArrayList<Cities>

    fun getCity(name: String): City?

    fun writeAQI(data: List<Cities>)

}