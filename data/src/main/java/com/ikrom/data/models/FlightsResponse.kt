package com.ikrom.data.models

import com.google.gson.annotations.SerializedName

data class FlightsResponse(
    @SerializedName("id")  val id: Int,
    @SerializedName("title") val airline: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: Price
)