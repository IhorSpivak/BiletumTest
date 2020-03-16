package com.example.biletum.interactors

interface Interactor<in P, out T> {
    suspend operator fun invoke(executeParams: P): T
}