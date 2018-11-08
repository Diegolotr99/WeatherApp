package com.diegogutierrez.weatherapp.domain.commands

import com.diegogutierrez.weatherapp.domain.mappers.ForecastDataMapper
import com.diegogutierrez.weatherapp.domain.model.ForecastList
import com.diegogutierrez.weatherapp.remote.ForecastRequest

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {

        val forecastRequest = ForecastRequest(zipCode)

        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())

    }
}