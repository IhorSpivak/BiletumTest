package com.example.biletum.base.interactor

interface InteractorWithoutParams<out T> {
    suspend operator fun invoke(): T
}