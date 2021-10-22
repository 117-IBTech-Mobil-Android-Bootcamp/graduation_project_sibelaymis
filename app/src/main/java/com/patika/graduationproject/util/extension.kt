package com.patika.graduationproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


inline fun<reified T : AppCompatActivity> Context.startActivity(block : Intent.() -> Unit = {}){

    val intent  = Intent(this , T::class.java)
    startActivity(
        intent.also {
            block.invoke(it)
        }
    )
}

fun Fragment.navigateFragment(id:Int,bundle: Bundle?){
    findNavController().navigate(id,bundle)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

//Display popup when fragment call on back
fun Fragment.onBack() =
    requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().finish()
        }

    })

inline fun<reified T> Fragment.saveData(key: String, list: MutableList<T>?){
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("FavoriteList",
        Context.MODE_PRIVATE
    )

    val myEdit = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(list)
    myEdit.putString(key, json)

    myEdit.commit()
}

inline fun<reified T> Fragment.getData(key : String): MutableList<T>? {
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("FavoriteList",
        Context.MODE_PRIVATE
    )
    var arrayItems: MutableList<T> = mutableListOf()
    val serializedObject = sharedPreferences.getString(key, null)
    if (serializedObject != null) {
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<T?>?>() {}.type
        arrayItems = gson.fromJson(serializedObject, type)
    }
    return  arrayItems
}

fun Fragment.showToast(messageToShow : String){
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_LONG).show()
}