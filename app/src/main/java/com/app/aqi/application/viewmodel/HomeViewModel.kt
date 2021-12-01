package com.app.aqi.application.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.aqi.application.pojo.CityDisplay
import com.app.aqi.application.util.Mapper
import com.app.aqi.data.local.pojo.City
import com.app.aqi.domain.usecases.FetchAQIUseCase
import com.app.aqi.domain.usecases.FetchCitiesUseCase
import com.app.aqi.domain.usecases.FetchCityUseCase
import com.app.aqi.domain.usecases.StopAQIUseCase
import com.app.aqi.utils.Constants
import com.app.aqi.utils.Constants.REFRESH_CITIES
import com.app.aqi.utils.Constants.REFRESH_CITY
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAQIUseCase: FetchAQIUseCase,
    private val stopAQIUseCase: StopAQIUseCase,
    private val fetchCityUseCase: FetchCityUseCase,
    private val FetchCitiesUseCase: FetchCitiesUseCase
) : ViewModel() {

    fun start() {
        fetchAQIUseCase.invoke()
    }

    fun stop() {
        stopAQIUseCase.invoke()
    }

    private fun getCities() {
        Log.v(Constants.TAG, "getCities ")

        FetchCitiesUseCase.invoke().map {
            Mapper.getDisplayObject(it)
        }
            .subscribe(object : SingleObserver<ArrayList<CityDisplay>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: ArrayList<CityDisplay>) {
                    _cityList.postValue(t)
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    fun getCity(city: String) {
        disposableCity = Observable.interval(0, REFRESH_CITY, TimeUnit.SECONDS).subscribe {
            refresh(city)
        }
    }

    var disposableCity: Disposable? = null
    var disposableCities: Disposable? = null

    private fun refresh(city: String) {
        fetchCityUseCase.invoke(city).subscribe(object : SingleObserver<City> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(t: City) {
                _city.postValue(t)
            }

            override fun onError(e: Throwable) {
            }

        })
    }

    fun stopCity() {
        disposableCity?.dispose()
    }

    fun stopFetch() {
        disposableCities?.dispose()
    }

    fun startFetch() {
        disposableCities = Observable.interval(0, REFRESH_CITIES, TimeUnit.SECONDS).subscribe {
            getCities()
        }
    }

    private val _cityList: MutableLiveData<ArrayList<CityDisplay>> =
        MutableLiveData<ArrayList<CityDisplay>>()
    val cityList: LiveData<ArrayList<CityDisplay>> = _cityList


    private val _city: MutableLiveData<City> = MutableLiveData<City>()
    val city = _city


}