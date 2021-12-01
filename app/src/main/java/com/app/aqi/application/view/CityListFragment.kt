package com.app.aqi.application.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aqi.R
import com.app.aqi.application.viewmodel.HomeViewModel
import com.app.aqi.databinding.FragmentCityListBinding
import com.app.aqi.utils.Constants.CITY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityListFragment : Fragment() {

    lateinit var binding: FragmentCityListBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: CityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_city_list, container, false)

        setUpUI()

        setupObservers()

        setupListeners()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.cityList.observe(viewLifecycleOwner, Observer {
            adapter?.setCities(it)
            Toast.makeText(context, R.string.toast_message2, Toast.LENGTH_LONG).show()
        })
    }

    private fun setUpUI() {
        binding?.rvCityList.layoutManager = LinearLayoutManager(context)
        binding?.rvCityList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        initFetch()
    }

    private fun setupListeners() {
        adapter?.setListeners(object : OnCityClick {
            override fun cityClicked(city: String) {
                val bundle = bundleOf(CITY to city)
                findNavController().navigate(R.id.action_cityList_to_cityGraphFragment, bundle)
            }
        })
    }

    private fun initFetch() {
        viewModel.startFetch()
    }


    override fun onPause() {
        super.onPause()
        viewModel.stopFetch()
    }

    interface OnCityClick {
        fun cityClicked(city: String)
    }

}