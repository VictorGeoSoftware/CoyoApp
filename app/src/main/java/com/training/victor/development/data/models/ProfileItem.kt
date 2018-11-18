package com.training.victor.development.data.models

import com.google.gson.annotations.SerializedName

data class ProfileItem(@SerializedName("id") val id: Int, @SerializedName("profile_picture") val profilePicture: String)