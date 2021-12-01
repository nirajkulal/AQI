package com.app.aqi.application.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.aqi.R
import com.app.aqi.application.viewmodel.HomeViewModel
import com.app.aqi.databinding.FragmentCityGraphBinding
import com.app.aqi.utils.Constants.CITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityGraphFragment : Fragment() {

    lateinit var city: String

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city = arguments?.get(CITY).toString()
    }

    lateinit var binding: FragmentCityGraphBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_city_graph, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        viewModel.getCity(city)
        setObserver()
    }

    private fun setObserver() {
        viewModel.city.observe(this, Observer {
            binding.chart.updateView(it)
            Toast.makeText(context, R.string.toast_message, Toast.LENGTH_LONG).show()
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopCity()
    }

}