package com.example.biletum.data.network.model.responses.contacts

import com.example.biletum.data.network.model.models.ContactItem

data class ContactsResponse(
    val result:Boolean,
    val data:List<ContactItem>
)