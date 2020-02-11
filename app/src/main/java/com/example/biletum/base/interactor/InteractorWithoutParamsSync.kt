package com.yaroslavtupalo.knocs.base.interactor

interface InteractorWithoutParamsSync<out T> {
    operator fun invoke(): T
}