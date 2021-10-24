package com.patika.graduationproject.ui.currentweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.patika.graduationproject.model.CurrentWeatherStateModel
import com.patika.graduationproject.Result
import com.patika.graduationproject.model.City
import com.patika.graduationproject.service.WeatherRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val currentWeatherResponse = MutableLiveData<CurrentWeatherStateModel>()
    val currentWeatherListResponse = MutableLiveData<MutableList<CurrentWeatherStateModel>>()
    val searchedWeatherResponse = MutableLiveData<List<City>>()
    val onError = MutableLiveData<Unit>()

    //    private val weatherRepository = WeatherRepository()
    var name = ""

    fun getCurrentWeather() {
        viewModelScope.launch {
            val response = weatherRepository.getCurrentWeather(name)
            when (response) {
                is Result.Success -> {
                    currentWeatherResponse.value = CurrentWeatherStateModel(response.data?.currentWeatherList?.get(0)!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }

    }

    fun getSearchedWeather() {
        viewModelScope.launch {
            val response = weatherRepository.getSearchedWeather(name)
            when (response) {
                is Result.Success -> {
                    searchedWeatherResponse.value = response.data!!
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun prepareCurrentWeathers() {

        viewModelScope.launch {

            weatherRepository.getCurrents().collect {
                val weatherList = mutableListOf<CurrentWeatherStateModel>()
                it.currentWeatherList.forEach {
                    weatherList.add(CurrentWeatherStateModel(it))
                }

                currentWeatherListResponse.value = weatherList
            }
        }
    }
}