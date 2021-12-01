package com.app.aqi.application.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.aqi.R
import com.app.aqi.application.pojo.CityDisplay
import com.app.aqi.databinding.CityItemBinding
import javax.inject.Inject

public class CityListAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    open class ViewHolder(val cityItemBinding: CityItemBinding) :
        RecyclerView.ViewHolder(cityItemBinding.root) {
    }
    private val cities: ArrayList<CityDisplay> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: CityItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.city_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).cityItemBinding.item = cities[position]

        holder.itemView.setOnClickListener {
            onCityClick?.cityClicked(cities[position].name.orEmpty())
        }
    }

    override fun getItemCount(): Int = cities.size


    fun setCities(it: ArrayList<CityDisplay>?) {
        cities.clear()
        it?.forEach {
            cities.add(it)
        }
        notifyDataSetChanged()
    }

    var onCityClick: CityListFragment.OnCityClick? = null
    fun setListeners(onCityClick: CityListFragment.OnCityClick) {
        this.onCityClick = onCityClick
    }

}