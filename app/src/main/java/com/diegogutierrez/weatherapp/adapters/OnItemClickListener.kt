package com.diegogutierrez.weatherapp.adapters

import com.diegogutierrez.weatherapp.domain.model.Forecast

interface OnItemClickListener {
    operator fun invoke (forecast: Forecast)
}