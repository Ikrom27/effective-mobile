package com.ikrom.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.ikrom.data.api.ArtistApiResponse
import com.ikrom.data.api.ArtistService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class Repository @Inject constructor(
    val artistService: ArtistService,
    val sharedPreferences: SharedPreferences
) {
    fun getArtistsList(): Single<ArtistApiResponse> {
        return artistService.getArtists()
    }

    fun saveOrigin(text: String){
        sharedPreferences.edit {
            putString(ORIGIN_KEY, text)
        }
    }

    fun getSavedOrigin(): String {
        return sharedPreferences.getString(ORIGIN_KEY, "") ?: ""
    }

    companion object {
        private const val ORIGIN_KEY = "origin"
    }
}