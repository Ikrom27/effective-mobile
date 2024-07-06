package com.ikrom.data.api

import com.ikrom.data.models.ArtistResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ArtistService {
    @GET("u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    fun getArtists(): Single<ArtistApiResponse>
}

data class ArtistApiResponse(
    val offers: List<ArtistResponse>
)