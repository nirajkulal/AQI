package com.app.aqi.utils

object Constants {

    const val API_URL ="wss://city-ws.herokuapp.com/"
    const val TAG ="AQI_APP"

    //Realm
    const val REALM_DATABASE_VERSION :Long  =1
    const val REALM_DATABASE_NAME  ="aqi.realm"

    const val TEXT_FEW_SEC = " seconds ago"
    const val UP_TO_DATE = "Up to date"
    const val TEXT_FEW_MIN = " minutes ago"
    const val CITY = "city"

    const val REFRESH_CITIES :Long  = 10
    const val REFRESH_CITY :Long= 30
}
