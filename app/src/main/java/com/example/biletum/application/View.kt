package com.example.biletum.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

internal fun View.updateWidth(width: Int) {
    val layoutParams = this.layoutParams
    layoutParams.width = width
    this.layoutParams = layoutParams
}

internal fun View.updateHeight(height: Int) {
    val layoutParams = this.layoutParams
    layoutParams.height = height
    this.layoutParams = layoutParams
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)