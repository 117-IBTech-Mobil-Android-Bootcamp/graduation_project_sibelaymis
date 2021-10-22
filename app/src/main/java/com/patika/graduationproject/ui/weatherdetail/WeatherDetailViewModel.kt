package com.patika.graduationproject.ui.weatherdetail

import android.app.Application
import androidx.lifecycle.*
import com.patika.graduationproject.Result
import com.patika.graduationproject.model.CurrentWeatherStateModel
import com.patika.graduationproject.model.WeatherStateModel
import com.patika.graduationproject.service.WeatherRepository
import kotlinx.coroutines.launch

class WeatherDetailViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherDetailResponse = MediatorLiveData<WeatherStateModel>()
    val onError = MutableLiveData<Unit>()
    var name=""

    fun getWeatherDetail() {
        viewModelScope.launch {
            val response=weatherRepository.getWeatherDetail(name)
            when(response){
                is Result.Success -> {
                    weatherDetailResponse.value = WeatherStateModel(response.data!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }
}