package com.ikrom.data.di

import com.ikrom.data.api.ArtistService
import com.ikrom.data.api.FlightsService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun ArtsitService(): ArtistService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ArtistService::class.java)
}

fun FlightsService(): FlightsService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(FlightsService::class.java)
}