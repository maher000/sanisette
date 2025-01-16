package com.maher.sanisettes.presentation

import com.maher.sanisettes.domain.model.Sanisette

data class SanisetteUiModel(
    val district: String,
    val address: String,
    val openHours: String,
    val manager: String,
    val source: String,
    val longitude: Double,
    val latitude: Double
)

fun Sanisette.toUiModel(): SanisetteUiModel = SanisetteUiModel(
    district = district,
    address = address,
    openHours = openHours,
    manager = manager,
    source = source,
    longitude = longitude,
    latitude = latitude

)