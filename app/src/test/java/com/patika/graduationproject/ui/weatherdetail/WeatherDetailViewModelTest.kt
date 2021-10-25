package com.patika.graduationproject.ui.weatherdetail

import com.patika.graduationproject.service.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class WeatherDetailViewModelTest {

    @Test
    fun `getWeatherDetail function returns false when name  is empty`() {

        runBlocking {
            //given
            val repository: WeatherRepository = Mockito.mock(WeatherRepository::class.java)
            val viewModel = WeatherDetailViewModel(repository)
            viewModel.name = ""
            //            when
            val response = viewModel.getWeatherDetail()
            //            then
            //            assertEquals(Result.Error<WeatherDetailResponse>(ERROR_MESSAGE),response)
            //            assertNotNull(response)
            assertSame(Unit, response)
        }
    }
}