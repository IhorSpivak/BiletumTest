package com.example.biletum.data.network.model.responses.events

data class CommentsListResponse(
    val result:Boolean,
    val data:List<Comments>
)