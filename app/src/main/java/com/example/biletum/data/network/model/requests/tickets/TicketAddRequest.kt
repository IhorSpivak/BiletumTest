package com.example.biletum.data.network.model.requests.tickets

import com.example.biletum.data.network.model.responses.tickets.Ticket

data class TicketAddRequest(
    val title  :String,
    val file  :String,
    val date_start  :String,
    val ident  :String

)