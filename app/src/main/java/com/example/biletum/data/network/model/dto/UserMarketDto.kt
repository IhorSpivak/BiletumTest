package com.example.biletum.data.network.model.dto

data class UserMarketDto(
    val enabled:Boolean?,
    val marketIconPath:String?,
    val marketName:String?,
    var status:String?,
    val userMarketName:String?,
    val userMarketSettingsId: Int?,
    val usernameForMarket: String?)