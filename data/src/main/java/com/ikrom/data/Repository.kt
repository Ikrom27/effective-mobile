package com.ikrom.data

import com.ikrom.data.api.ArtistApiResponse
import com.ikrom.data.api.ArtistService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class Repository @Inject constructor(
    val artistService: ArtistService
) {
    fun getArtistsList(): Single<ArtistApiResponse> {
        return artistService.getArtists()
    }
}