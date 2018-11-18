package com.training.victor.development.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostViewModel(val userId: Int, val id: Int, val title: String, val body: String) : Parcelable