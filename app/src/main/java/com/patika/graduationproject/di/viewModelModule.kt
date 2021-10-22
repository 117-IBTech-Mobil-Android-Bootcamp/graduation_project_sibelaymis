package com.patika.graduationproject.di

import com.patika.graduationproject.ui.currentweather.CurrentWeatherViewModel
import com.patika.graduationproject.ui.weatherdetail.WeatherDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrentWeatherViewModel(get()) }
    viewModel { WeatherDetailViewModel(get()) }
}