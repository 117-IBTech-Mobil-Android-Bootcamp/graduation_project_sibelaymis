package com.patika.graduationproject.ui.currentweather

import androidx.lifecycle.*
import com.patika.graduationproject.model.CurrentWeatherStateModel
import com.patika.graduationproject.Result
import com.patika.graduationproject.model.City
import com.patika.graduationproject.service.WeatherRepository
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
                    currentWeatherResponse.value = CurrentWeatherStateModel(response.data!!)
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

    fun prepareCurrentWeathers() {

        viewModelScope.launch {

            val movieListResponseFetchedFromDB = weatherRepository.getListAsync()
            var weatherList = mutableListOf<CurrentWeatherStateModel>()
            movieListResponseFetchedFromDB.currentWeatherList.forEach {
                weatherList.add(CurrentWeatherStateModel(it))
            }

            currentWeatherListResponse.value = weatherList
        }
    }
}