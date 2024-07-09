package com.ikrom.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.ikrom.data.api.ArtistServiceResponse
import com.ikrom.data.api.ArtistService
import com.ikrom.data.api.FlightsService
import com.ikrom.data.api.FlightsServiceResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class Repository @Inject constructor(
    private val artistService: ArtistService,
    private val sharedPreferences: SharedPreferences,
    private val flightsService: FlightsService
) {
    fun getArtistsList(): Single<ArtistServiceResponse> {
        return artistService.getArtists()
    }

    fun getFlightsList(): Single<FlightsServiceResponse> {
        return flightsService.getTickets()
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