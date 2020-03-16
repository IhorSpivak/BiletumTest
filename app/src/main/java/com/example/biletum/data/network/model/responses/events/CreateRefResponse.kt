package com.example.biletum.data.network.model.responses.events

import com.example.biletum.data.network.model.models.RefData

data class CreateRefResponse(
    val result:Boolean,
    val data: RefData
)