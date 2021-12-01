package com.app.aqi.data.local.util

import com.app.aqi.data.local.pojo.Cities
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object JSONMapper {
    @Throws(JSONException::class)
    fun getCities(text: String): List<Cities> {
        val cities: MutableList<Cities> = mutableListOf()
        val array = JSONArray(text)
        for (loop in 0 until array.length()) {
            if (array.getJSONObject(loop) is JSONObject) {
                val objectCity = array.getJSONObject(loop)
                val city = Cities(
                    objectCity.getString("city"),
                    objectCity.getString("aqi"),
                    Helper.getTime()
                )
                cities.add(city)
            }
        }
        return cities
    }
}