package com.app.aqi.utils.app

import android.app.Application
import com.app.aqi.utils.Constants.REALM_DATABASE_NAME
import com.app.aqi.utils.Constants.REALM_DATABASE_VERSION
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class AqiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfiguration: RealmConfiguration =
            RealmConfiguration
                .Builder()
                .name(REALM_DATABASE_NAME)
                .compactOnLaunch()
                .schemaVersion(REALM_DATABASE_VERSION)
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}