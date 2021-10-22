package com.patika.graduationproject.ui.weatherdetail

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.patika.graduationproject.BaseFragment
import com.patika.graduationproject.R
import com.patika.graduationproject.databinding.FragmentWeatherDetailBinding
import com.patika.graduationproject.model.Current
import com.patika.graduationproject.model.ForeCastDay
import com.patika.graduationproject.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel,FragmentWeatherDetailBinding>() {
    private var weatherDetailAdapter: WeatherDetailAdapter? = null
    private var weatherList = mutableListOf<Current>()
    override val viewModel: WeatherDetailViewModel by viewModel()

    override fun getLayoutID(): Int =R.layout.fragment_weather_detail

    override fun observeLiveData() {
        viewModel.weatherDetailResponse.observe(this, {

            dataBinding.weatherState = it
            dataBinding.executePendingBindings()

            weatherList.addAll(it.getList())
            weatherDetailAdapter?.notifyDataSetChanged()
        })

        viewModel.onError.observe(this, {

            showToast("Bir hata meydana geldi")

        })
    }

    override fun prepareView() {
        backgroundColorChange(R.color.grey)
        weatherDetailAdapter=   WeatherDetailAdapter(weatherList)
        dataBinding.recyclerView.adapter =weatherDetailAdapter

        val cityName=arguments?.getString("cityName")
        viewModel.name=cityName!!
        viewModel.getWeatherDetail()
    }



}