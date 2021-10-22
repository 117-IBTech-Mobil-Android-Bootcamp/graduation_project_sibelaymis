package com.patika.graduationproject.di

import com.patika.graduationproject.service.WeatherAPI
import com.patika.graduationproject.util.BASE_URL_FOR_WEATHER_APP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
}



fun provideHttpClient() : OkHttpClient{
    //logging interceptor
    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //adding logging interceptor to okhttp
    val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return httpClient
}
fun provideRetrofit(httpClient: OkHttpClient) : Retrofit {

    return Retrofit.Builder()
        .baseUrl(BASE_URL_FOR_WEATHER_APP)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}


fun provideWeatherApi(retrofit: Retrofit) : WeatherAPI{
    return  retrofit.create(WeatherAPI::class.java)
}