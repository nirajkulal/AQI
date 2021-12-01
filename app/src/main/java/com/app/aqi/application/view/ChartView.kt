package com.app.aqi.application.view

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.app.aqi.application.util.ColorCodeMapper
import com.app.aqi.data.local.pojo.City
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*


fun LineChart.updateView(city: City) {
    isDragEnabled = false
    description.isEnabled = false

    setValues(city, this)

    legend.isEnabled = false
    axisRight.isEnabled = false
    xAxis.isEnabled = true
    xAxis.setDrawAxisLine(false)
    xAxis.textSize = 10f
    xAxis.textColor = Color.BLACK
    xAxis.gridColor = Color.WHITE

    axisLeft.setDrawAxisLine(false)
    axisLeft.textSize = 10f
    axisLeft.textColor = Color.BLACK
    axisLeft.gridColor = Color.TRANSPARENT

    setPinchZoom(false)
    isDoubleTapToZoomEnabled = false

    setScaleEnabled(false)
    invalidate()
}

private fun setValues(city: City, lineChart: LineChart) {
    val values: ArrayList<Entry> = getEntries(city)

    val set = LineDataSet(values, "")
    set.lineWidth = 2f
    set.circleRadius = 0f
    set.setDrawIcons(false)
    set.setDrawCircleHole(false)
    set.valueTextSize = 9f
    set.valueTextSize = 9f
    set.setDrawFilled(true)
    set.setDrawValues(false)
    set.highLightColor = Color.TRANSPARENT
    set.fillAlpha = 1
    set.setDrawFilled(true)
    set.highlightLineWidth = 0f
    set.setDrawHighlightIndicators(false)
    set.mode = LineDataSet.Mode.LINEAR
    set.setDrawCircles(false)

    set.formSize = 15f

    if (city.aqis[0].aqiValue != null)
        set.color =
            lineChart.context.resources.getColor(ColorCodeMapper.getColor(city.aqis[0].aqiValue))
    val drawable = ContextCompat.getDrawable(
        lineChart.context,
        ColorCodeMapper.getColor(city.aqis[0].aqiValue)
    )
    set.fillDrawable = drawable

    val dataSets = ArrayList<ILineDataSet>()
    dataSets.add(set)

    val data = LineData(dataSets)
    lineChart.data = data

}

private fun getEntries(city: City): ArrayList<Entry> {
    val values = ArrayList<Entry>()
    for (loop in city.aqis.indices) {
        val entry = Entry()
        if (city.aqis[loop].aqiValue != null) {
            entry.x = loop.toFloat()
            entry.y = city?.aqis[loop]?.aqiValue?.toFloat()!!
            values.add(entry)
        }
    }
    return values
}