package com.ikrom.data.models

data class ArtistResponse(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price,
)

data class Price(
    val value: Int
)