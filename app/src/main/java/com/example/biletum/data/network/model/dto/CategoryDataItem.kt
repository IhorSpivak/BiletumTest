package com.example.biletum.data.network.model.dto

import android.os.Parcelable
import com.example.biletum.data.network.model.models.CategoryItem
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class CategoryDataItem(
    val list: List<CategoryItem>


) : Parcelable