package com.app.aqi.data.local.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AQIValuesRealm : RealmObject() {

    @PrimaryKey
    var key: String? = null
    var time: Long? = null
    var aqiValue: String? = null
    var city: String? = null

}