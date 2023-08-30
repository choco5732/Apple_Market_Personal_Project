package com.android.personal_project_apple_market.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Apple(
    val picture: Int, val writer: String, val title: String, val content: String,
    val price: Int, val address: String, val reply: String, val like: String
) : Parcelable
