package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName

data class ProfileDetailResp(
    @SerializedName("id") val id:Int,
    @SerializedName("first_name") val firstName:Int,
    @SerializedName("last_name") val lastName:Int,
    @SerializedName("profile_picture") val profilePicture:Int)