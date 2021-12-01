package com.app.aqi.application.util

import com.app.aqi.application.pojo.CityDisplay
import com.app.aqi.data.local.pojo.Cities
import com.app.aqi.data.local.util.Helper
import com.app.aqi.utils.Constants.TEXT_FEW_MIN
import com.app.aqi.utils.Constants.TEXT_FEW_SEC
import com.app.aqi.utils.Constants.UP_TO_DATE
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object Mapper {
    fun getDisplayObject(it: ArrayList<Cities>?): ArrayList<CityDisplay> {
        val outPut = ArrayList<CityDisplay>()
        it?.forEach {
            outPut.add(
                CityDisplay(
                    it.name,
                    getDisplayAQI(it.lastAQIValue),
                    getUpdatedTime(it.lastUpdatedTime),
                    ColorCodeMapper.getColor(it.lastAQIValue)
                )
            )
        }
        return outPut
    }

    private fun getUpdatedTime(lastUpdatedTime: Long): String {
        var diff: Long = (Helper.getTime() - lastUpdatedTime) / 1000
        var zero: Long = 0

        if (diff == zero) {
            return UP_TO_DATE
        }

        return when {
            diff < 60 -> {
                "$diff $TEXT_FEW_SEC"
            }
            diff < (60 * 60) -> {
                diff /= 60
                "$diff $TEXT_FEW_MIN"
            }
            else -> {
                ""
            }
        }

    }

    private fun getDisplayAQI(lastAQIValue: String?): String? {
        if (lastAQIValue == null)
            return "0"
        val decimal = BigDecimal(lastAQIValue).setScale(2, RoundingMode.HALF_EVEN)
        return decimal.toString()
    }
}