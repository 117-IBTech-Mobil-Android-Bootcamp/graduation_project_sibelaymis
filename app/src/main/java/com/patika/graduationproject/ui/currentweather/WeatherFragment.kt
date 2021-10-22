package com.patika.graduationproject.ui.currentweather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.patika.graduationproject.model.CurrentWeatherStateModel
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.patika.graduationproject.*
import com.patika.graduationproject.databinding.FragmentWeatherBindingImpl


class WeatherFragment : BaseFragment<CurrentWeatherViewModel, FragmentWeatherBindingImpl>() {

    private var currentWeatherStateModel = mutableListOf<CurrentWeatherStateModel>()
    private var cities = arrayOf<String>()
    lateinit var adapter:ArrayAdapter<String>

    override val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutID(): Int = R.layout.fragment_weather

    override fun observeLiveData() {
        viewModel.currentWeatherResponse.observe(this, {

            dataBinding.currentWeatherState = it
            dataBinding.executePendingBindings()
            it.let {
                currentWeatherStateModel.asReversed().add(it)
                setUpImageViewPager(currentWeatherStateModel)
            }
        })

        viewModel.currentWeatherListResponse.observe(this, {
            currentWeatherStateModel=it
            setUpImageViewPager(currentWeatherStateModel)
            if(!currentWeatherStateModel.isEmpty()) {
                val notingChoose=view?.findViewById<AppCompatTextView>(R.id.notingChoose)
                notingChoose?.gone()
            }
        })

        viewModel.searchedWeatherResponse.observe(this, {

            var index=0
            val mutableArray= mutableListOf<String>()
            it.forEach { city ->
                mutableArray.add(index,city.name)
                    index++
                }
            this.cities = mutableArray.toTypedArray()
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cities)
            autoCompleteTextView.setAdapter(adapter)

        })
    }

    override fun prepareView() {
        backgroundColorChange(R.color.grey)
        //db'den kaydedilen şehirler alınacak
        viewModel.prepareCurrentWeathers()
        setUpImageViewPager(currentWeatherStateModel)

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s?.length!! >2) {
                    viewModel.name = s.toString()
                    viewModel.getSearchedWeather()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        autoCompleteTextView.onItemClickListener= AdapterView.OnItemClickListener{
                parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            viewModel.name=selectedItem
            viewModel.getCurrentWeather()
        }


    }

    private fun setUpImageViewPager(currentWeatherStateModel: List<CurrentWeatherStateModel>) {
        if (currentWeatherStateModel.isNotEmpty()) {
            viewPager.adapter = WeatherPagerAdapter(requireContext(), currentWeatherStateModel){
                    clickedObject ->
                navigateFragment(
                    R.id.action_weatherFragment_to_weatherDetailFragment,
                    Bundle().apply {
                        putString("cityName", clickedObject.getName())
                    })
            }
        }
    }
}