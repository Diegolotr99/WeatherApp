package com.diegogutierrez.weatherapp.activities

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.diegogutierrez.weatherapp.R
import com.diegogutierrez.weatherapp.adapters.ForecastListAdapter
import com.diegogutierrez.weatherapp.domain.commands.RequestForecastCommand
import com.diegogutierrez.weatherapp.domain.model.Forecast
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGet: Button = find(R.id.btn_get_weather)
        val editText: EditText = find(R.id.edit_text_zip)
        btnGet.setOnClickListener { fillList(editText.text.toString()) }

    }

    private fun fillList(zipCode: String) {
        val forecastList: RecyclerView = find(R.id.forecast_list)
        val editTextCity: TextView = find(R.id.txt_city_data)
        val editTextCountry: TextView = find(R.id.txt_country_data)
        val constrainLayoutCityInfo: ConstraintLayout = find(R.id.cl_info)


        forecastList.layoutManager = LinearLayoutManager(this)
        doAsync {
            val result = RequestForecastCommand(zipCode).execute()

            uiThread {
                editTextCity.text=result.city
                editTextCountry.text=result.country

                forecastList.adapter = ForecastListAdapter(result,
                        object : ForecastListAdapter.OnItemClickListener {
                            override fun invoke(forecast: Forecast) {
                                toast(forecast.date)
                            }
                        }

                )
                constrainLayoutCityInfo.visibility = View.VISIBLE

            }
        }

    }
}
