package com.example.biletum.data.network.model.responses.events

import com.example.biletum.data.network.model.models.Comments

data class CommentsListResponse(
    val result:Boolean,
    val data:List<Comments>
)