package com.patika.graduationproject.ui.weatherdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.patika.graduationproject.R
import com.patika.graduationproject.databinding.DetailRowBinding
import com.patika.graduationproject.model.Current
import com.patika.graduationproject.model.ForeCastDay
import com.patika.graduationproject.model.WeatherDetailStateModel

class WeatherDetailAdapter(
    private val detailList: List<Current>
) : RecyclerView.Adapter<DetailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
            return DetailViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.detail_row,parent,false))
    }


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val foreCast = detailList[position]
        holder.populate(foreCast)
        }

    override fun getItemCount(): Int = detailList.size


}

class DetailViewHolder(private val binding: DetailRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun populate(foreCast: Current) {
        binding.detailState = WeatherDetailStateModel(foreCast)
        binding.executePendingBindings()
    }

}

