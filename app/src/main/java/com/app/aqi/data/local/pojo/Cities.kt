package com.app.aqi.data.local.pojo

import com.app.aqi.R
import io.realm.RealmList

data class Cities(
    val name: String? = null,
    val lastAQIValue: String = "",
    val lastUpdatedTime: Long =0,

)