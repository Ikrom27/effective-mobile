package com.ikrom.data.api

import com.google.gson.annotations.SerializedName
import com.ikrom.data.models.TicketsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface TicketsService {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    fun getAllTickets(): Single<TicketsServiceResponse>
}

data class TicketsServiceResponse(
    @SerializedName("tickets") val tickets: List<TicketsResponse>
)