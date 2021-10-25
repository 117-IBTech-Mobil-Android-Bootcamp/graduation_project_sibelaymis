package com.patika.graduationproject.service

import com.patika.graduationproject.Result
import com.patika.graduationproject.db.WeatherDao
import com.patika.graduationproject.service.response.WeatherDetailResponse
import com.patika.graduationproject.util.ERROR_MESSAGE
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class WeatherRepositoryTest {
    @Test
    fun `getWeatherDetail function returns false when name  is empty`() {

        runBlocking {
            //given
            val name = ""
            val dao: WeatherDao = Mockito.mock(WeatherDao::class.java)
            val api: WeatherAPI = Mockito.mock(WeatherAPI::class.java)
//            when
            val response=WeatherRepository(api, dao).getWeatherDetail(name)
//            then
//            assertEquals(Result.Error<WeatherDetailResponse>(ERROR_MESSAGE),response)
//            assertNotNull(response)
            assertSame(Result.Error<WeatherDetailResponse>(ERROR_MESSAGE),response.data)
        }
    }
}