package com.diegogutierrez.weatherapp.remote

import com.google.gson.Gson
import java.io.BufferedReader
import java.lang.Exception
import java.net.URL

class ForecastRequest(private val zipCode: String) {
    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }
        fun execute(): ForecastResult {
            
            val forecastJsonStr: String
            try {
                forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
                return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)

            }catch (ex:Exception){
                ex.stackTrace[0]
                return ForecastResult(city = City(0, "zip not found", Coordinates(0f,0f), country = "zip not found", population = 0), list = listOf())
            }

        }
}
