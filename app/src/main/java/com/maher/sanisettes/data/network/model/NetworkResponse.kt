package com.maher.sanisettes.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("results")
    val data: T,
)