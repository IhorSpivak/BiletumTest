package com.example.biletum.data.local.db.mappers

import com.example.biletum.data.network.model.dto.UserApiModel

import com.yaroslavtupalo.knocs.data.local.db.entities.UserEntity

//
//class UserEntityMapper:Mapper<UserApiModel,UserEntity> {
//    override suspend fun map(from: UserApiModel): UserEntity {
//        return UserEntity(from.access_token,from.token_type,from.expires_in,from.scope)
//    }
//}