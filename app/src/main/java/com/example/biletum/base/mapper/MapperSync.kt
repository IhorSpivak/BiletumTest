package com.yaroslavtupalo.knocs.base.mapper

interface MapperSync<F, T> {
    fun map(from: F): T

    fun map(from: List<F>): List<T> {
        return MutableList(from.size) {
            map(from[it])
        }
    }
}