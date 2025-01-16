package com.maher.sanisettes.data.network.model

import com.maher.sanisettes.domain.model.Sanisette
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SanisetteNetwork(
    @SerialName("arrondissement")
    val district: String,
    @SerialName("adresse")
    val address: String,
    @SerialName("horaire")
    val openHours: String?,
    @SerialName("gestionnaire")
    val manager: String,
    val source: String,
    @SerialName("geo_point_2d")
    val location: SanisetteLocation
)

@Serializable
data class SanisetteLocation(
    @SerialName("lon")
    val longitude: Double,
    @SerialName("lat")
    val latitude: Double
)


fun SanisetteNetwork.toDomainModel(): Sanisette = Sanisette(
    district = district,
    address = address,
    openHours = openHours ?: "N/A",
    manager = manager,
    source = source,
    latitude = location.latitude,
    longitude = location.longitude
)
