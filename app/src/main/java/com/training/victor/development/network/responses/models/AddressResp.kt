package com.training.victor.development.network.responses.models

import com.google.gson.annotations.SerializedName

data class AddressResp (val street: String,
                        val suite: String,
                        val city: String,
                        @SerializedName("zipcode") val zipCode: String,
                        val geo: CoordinatesResp)