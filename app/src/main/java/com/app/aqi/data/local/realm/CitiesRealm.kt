package com.app.aqi.data.local.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CitiesRealm : RealmObject() {

    @PrimaryKey
    var name: String? = null
    var lastAQIValue: String = ""
    var lastUpdatedTime: Long =0

}