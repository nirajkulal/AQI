package com.app.aqi.data.local.pojo

data class City(
    val city: Cities,
    var aqis : MutableList<AQIValues>
)
