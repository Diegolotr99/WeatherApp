package com.diegogutierrez.weatherapp.domain.commands

interface Command <out T> {
    fun execute(): T
}