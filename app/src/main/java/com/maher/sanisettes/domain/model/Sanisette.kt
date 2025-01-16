package com.maher.sanisettes.domain.model

data class Sanisette(
    val district: String,
    val address: String,
    val openHours: String,
    val manager: String,
    val source: String,
    val longitude: Double,
    val latitude: Double
)
