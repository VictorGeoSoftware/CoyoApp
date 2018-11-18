package com.training.victor.development.network.responses

import com.google.gson.annotations.SerializedName
import com.training.victor.development.network.responses.models.AddressResp
import com.training.victor.development.network.responses.models.CompanyResp

data class UserResp (val id: Int,
                     val name: String,
                     @SerializedName("username") val userName: String,
                     val email: String,
                     val address: AddressResp,
                     val phone: String,
                     val website: String,
                     val company: CompanyResp)