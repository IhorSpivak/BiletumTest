package com.example.biletum.data.network.model.models

data class Country(
    val id:Int,
    val name:String,
    val iso2:String,
    val iso3:String,
    val phonecode:String,
    val capital:String,
    val currency:String
)