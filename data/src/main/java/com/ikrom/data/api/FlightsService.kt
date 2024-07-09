package com.ikrom.data.api

import com.ikrom.data.models.FlightsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FlightsService {
    @GET("u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    fun getTickets() : Single<FlightsServiceResponse>
}

data class FlightsServiceResponse(
    val tickets_offers: List<FlightsResponse>
)