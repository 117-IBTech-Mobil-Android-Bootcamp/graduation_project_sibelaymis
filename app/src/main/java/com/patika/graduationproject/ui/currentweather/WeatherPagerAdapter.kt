package com.patika.graduationproject.ui.currentweather

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.patika.graduationproject.R
import com.patika.graduationproject.model.CurrentWeatherStateModel
import android.graphics.BitmapFactory
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import java.io.InputStream
import java.net.URL


class WeatherPagerAdapter(context: Context, weatherList: List<CurrentWeatherStateModel>,private val block: (CurrentWeatherStateModel) -> Unit) :PagerAdapter() {

    private val context: Context = context
    private val weatherList: List<CurrentWeatherStateModel> = weatherList

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val imageUrl: String = weatherList[position].getWeatherStateIconUrl()
        val layout = inflater.inflate(R.layout.fragment_current, collection, false) as ViewGroup
        val weatherIcon: ImageView = layout.findViewById(R.id.weatherIcon)
        val image: InputStream = URL(imageUrl).openStream()
        val bmp = BitmapFactory.decodeStream(image)
        weatherIcon.setImageBitmap(bmp)
        val cityName:AppCompatTextView=layout.findViewById(R.id.cityName)
        val weatherType:AppCompatTextView=layout.findViewById(R.id.weatherType)
        val tempCelsius:AppCompatTextView=layout.findViewById(R.id.temp_celsius)
        val tempFahrenheit:AppCompatTextView=layout.findViewById(R.id.temp_fahrenheit)
        val tempCelsiusFeeling:AppCompatTextView=layout.findViewById(R.id.temp_celsius_feeling)
        val tempFahrenheitFeeling:AppCompatTextView=layout.findViewById(R.id.temp_fahrenheit_feeling)
        val tempCelsiusUnit:AppCompatImageView=layout.findViewById(R.id.temp_celsius_unit)
        val tempFahrenheitUnit:AppCompatImageView=layout.findViewById(R.id.temp_fahrenheit_unit)
        val tempCelsiusUnitFeeling:AppCompatImageView=layout.findViewById(R.id.temp_celsius_unit_feeling)
        val tempFahrenheitUnitFeeling:AppCompatImageView=layout.findViewById(R.id.temp_fahrenheit_unit_feeling)
        val detailBtn:AppCompatButton=layout.findViewById(R.id.detail_btn)

        cityName.text=weatherList[position].getName()
        weatherType.text=weatherList[position].getWeatherText()
        tempCelsius.text=weatherList[position].getTempCelsius()
        tempFahrenheit.text=weatherList[position].getTempFahrenheit()
        tempCelsiusFeeling.text=weatherList[position].getTempCelsiusFeels()
        tempFahrenheitFeeling.text=weatherList[position].getTempFahrenheitFeels()
        tempCelsiusUnit.setImageResource(R.drawable.ic_celsius)
        tempFahrenheitUnit.setImageResource(R.drawable.ic_fahrenheit)
        tempCelsiusUnitFeeling.setImageResource(R.drawable.ic_celsius)
        tempFahrenheitUnitFeeling.setImageResource(R.drawable.ic_fahrenheit)
        detailBtn.setBackgroundResource(R.drawable.rounded_button_purple)
        detailBtn.text = "Detail"
        detailBtn.setOnClickListener {
                block.invoke(weatherList[position])
            }
        collection.addView(layout)
        return layout
    }

//    fun onClickDetail(){
//        val inflater = LayoutInflater.from(context)
//        val layout = inflater.inflate(R.layout.fragment_current, V, false) as ViewGroup
//        val detailBtn:AppCompatButton=layout.findViewById(R.id.detail_btn)
//        fun setOnItemClickListener(
//            block: () -> Unit
//        ) {
//            detailBtn.setOnClickListener {
//
//                block.invoke()
//            }
//        }
//    }
    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount(): Int {
        return weatherList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}