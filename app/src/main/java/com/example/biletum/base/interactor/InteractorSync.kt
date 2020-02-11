package com.example.biletum.base.interactor

interface InteractorSync<in P, out T> {
    operator fun invoke(executeParams: P): T
}