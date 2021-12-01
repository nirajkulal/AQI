package com.app.aqi.data.reposistory

import com.app.aqi.data.local.LocalDB
import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.pojo.City
import com.app.aqi.data.network.API
import com.app.aqi.domain.repository.AQIDataRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AQIDataRepositoryImp @Inject constructor(
    val api: API,
    val localDB: LocalDB
) : AQIDataRepository {

    override fun connectWH() {
        api.connectWS()
    }

    override fun closeWH() {
        api.disconnectWS()
    }

    override fun fetchCities(): Single<ArrayList<Cities>> {
        return Single.create {
            it.onSuccess(localDB.getCities())
        }
    }


    override fun fetchCiy(name: String) : Single<City> {
        return Single.create {
            it.onSuccess(
                localDB.getCity(name)
            )
        }
    }
}