package com.example.biletum.base.interactor

interface Interactor<in P, out T> {
    suspend operator fun invoke(executeParams: P): T
}