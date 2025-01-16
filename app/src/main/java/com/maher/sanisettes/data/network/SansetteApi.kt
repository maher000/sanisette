package com.maher.sanisettes.data.network

import com.maher.sanisettes.data.network.model.NetworkResponse
import com.maher.sanisettes.data.network.model.SanisetteNetwork
import retrofit2.http.GET
import retrofit2.http.Query

const val DATA_LIMIT = 20

interface SanisetteApi {
    /**
     * Get the list of sanisettes
     * @param limit the number of sanisettes to return
     * @param offset the offset of the first sanisette to return
     */
    @GET(value = "records")
    suspend fun getSanisettes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NetworkResponse<List<SanisetteNetwork>>

    /**
     * Get the list of sanisettes that respect the filter
     * @param where the filter to apply
     * @param limit the number of sanisettes to return
     * @param offset the offset of the first sanisette to return
     * exp: "75001" for first district of Paris
     */
    @GET(value = "records")
    suspend fun getSanisettesBy(
        @Query("where") where: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NetworkResponse<List<SanisetteNetwork>>
}