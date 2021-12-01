package com.app.aqi.data.local.util

import com.app.aqi.data.local.pojo.AQIValues
import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.realm.AQIValuesRealm
import com.app.aqi.data.local.realm.CitiesRealm

object RealmMapper {
    fun getCity(city: CitiesRealm): Cities {
        city.run {
            return Cities(
                name,
                lastAQIValue.orEmpty(),
                lastUpdatedTime
            )
        }
    }

    fun getAQI(aqi: AQIValuesRealm): AQIValues {
        aqi.run {
            return AQIValues(
                key,
                time,
                aqiValue,
                city
            )
        }
    }

}