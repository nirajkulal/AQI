package com.app.aqi.data.local

import com.app.aqi.data.local.pojo.AQIValues
import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.pojo.City
import com.app.aqi.data.local.realm.AQIValuesRealm
import com.app.aqi.data.local.realm.CitiesRealm
import com.app.aqi.data.local.util.RealmMapper
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class RealmDBImp @Inject constructor() : LocalDB {

    private var NAME = "name"
    private var CITY = "city"

    override fun getCities(): ArrayList<Cities> {
        val realm = Realm.getDefaultInstance()
        realm.use { r ->
            val result: ArrayList<Cities> = ArrayList()
            val city: RealmResults<CitiesRealm> =
                r.where(CitiesRealm::class.java).findAll()
            city.filterNotNull().forEach {
                result.add(RealmMapper.getCity(it))
            }
            return result
        }
    }

    override fun getCity(name: String): City? {
        val realm = Realm.getDefaultInstance()
        realm.use { r ->
            val city: CitiesRealm =
                r.where(CitiesRealm::class.java).equalTo(NAME, name).findFirst() ?: return null
            var res = City(RealmMapper.getCity(city), mutableListOf())
            val aqis: RealmResults<AQIValuesRealm> =
                r.where(AQIValuesRealm::class.java).equalTo(CITY, name).findAll() ?: return res
            val aqiList: MutableList<AQIValues> = mutableListOf()
            aqis.filterNotNull().forEach {
                aqiList.add(RealmMapper.getAQI(it))
            }
            res.aqis =aqiList
            return res
        }
    }

    override fun writeAQI(data: List<Cities>) {
        data.forEach {
            val realm = Realm.getDefaultInstance()
            realm?.executeTransaction { r: Realm ->
                writeCity(r, it)
                writeAQIS(r, it)
            }

        }
    }

    private fun writeAQIS(r: Realm, it: Cities) {
        val aql = r.createObject(AQIValuesRealm::class.java, "${it.name}-${it.lastUpdatedTime}")
        aql?.apply {
            time = it.lastUpdatedTime
            aqiValue = it.lastAQIValue
            city = it.name
        }
    }

    private fun writeCity(r: Realm, it: Cities) {
        var city: CitiesRealm? =
            r.where(CitiesRealm::class.java).equalTo(NAME, it.name).findFirst()
        if (city == null) {
            city = r.createObject(CitiesRealm::class.java, it.name)
        }
        city?.apply {
            lastUpdatedTime = it.lastUpdatedTime
            lastAQIValue = it.lastAQIValue
        }
    }
}