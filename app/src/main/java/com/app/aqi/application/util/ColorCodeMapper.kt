package com.app.aqi.application.util

import com.app.aqi.R

object ColorCodeMapper {
    fun getColor(lastAQIValue: String?): Int {
        if (lastAQIValue == null)
            return R.color.white
        if (lastAQIValue.isEmpty())
            return R.color.white
        val aqiD = lastAQIValue.toDouble()

        when {
            aqiD <= 50 -> return R.color.zone1
            aqiD <= 100 -> return R.color.zone2
            aqiD <= 200 -> return R.color.zone3
            aqiD <= 300 -> return R.color.zone4
            aqiD <= 400 -> return R.color.zone5
            aqiD <= 500 -> return R.color.zone6
        }
        return R.color.white
    }
}