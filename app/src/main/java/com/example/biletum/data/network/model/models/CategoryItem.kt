package com.example.biletum.data.network.model.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class CategoryItem(
    val name:String,
    val id:Int,
    var isCheked :Boolean = false

) : Parcelable