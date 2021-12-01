package com.app.aqi.application.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.aqi.R
import com.app.aqi.application.viewmodel.HomeViewModel
import com.app.aqi.databinding.ActivityHomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeActivityBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_activity)
        viewModel.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stop()
    }

}