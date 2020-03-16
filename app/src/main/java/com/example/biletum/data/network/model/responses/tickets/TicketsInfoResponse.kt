package com.example.biletum.data.network.model.responses.tickets

import com.example.biletum.data.network.model.models.Ticket

data class TicketsInfoResponse(
    val result :Boolean,
    val data : Ticket

)