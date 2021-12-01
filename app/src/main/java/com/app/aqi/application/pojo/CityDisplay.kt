package com.app.aqi.application.pojo

import com.app.aqi.R

data class CityDisplay(
    val name: String? = null,
    val lastAQIValue: String? = null,
    val lastUpdatedTime: String? = "",
    val color :Int = R.color.zone2
)