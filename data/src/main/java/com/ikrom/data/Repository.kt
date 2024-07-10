package com.ikrom.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.ikrom.data.api.ArtistServiceResponse
import com.ikrom.data.api.ArtistService
import com.ikrom.data.api.FlightsService
import com.ikrom.data.api.FlightsServiceResponse
import com.ikrom.data.api.TicketsService
import com.ikrom.data.api.TicketsServiceResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class Repository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val artistService: ArtistService,
    private val flightsService: FlightsService,
    private val ticketsService: TicketsService
) {
    fun getArtistsList(): Single<ArtistServiceResponse> {
        return artistService.getArtists()
    }

    fun getFlightsList(): Single<FlightsServiceResponse> {
        return flightsService.getTickets()
    }

    fun getAllTickets(): Single<TicketsServiceResponse>{
        return ticketsService.getAllTickets()
    }

    fun saveOrigin(text: String){
        sharedPreferences.edit {
            putString(ORIGIN_KEY, text)
        }
    }

    fun getSavedOrigin(): String {
        val origin = sharedPreferences.getString(ORIGIN_KEY, "") ?: ""
        return origin
    }

    companion object {
        private const val ORIGIN_KEY = "origin"
    }
}