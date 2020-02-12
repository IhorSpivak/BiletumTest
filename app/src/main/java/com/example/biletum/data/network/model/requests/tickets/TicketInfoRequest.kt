package com.example.biletum.data.network.model.requests.tickets

import com.example.biletum.data.network.model.responses.tickets.Ticket


data class TicketInfoRequest(
    val data :Ticket,
    val result :Boolean

)