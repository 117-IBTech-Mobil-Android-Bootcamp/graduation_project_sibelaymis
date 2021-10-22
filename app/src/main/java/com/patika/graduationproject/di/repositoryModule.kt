package com.patika.graduationproject.di

import com.patika.graduationproject.service.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
   factory { WeatherRepository(get(),get()) }
}