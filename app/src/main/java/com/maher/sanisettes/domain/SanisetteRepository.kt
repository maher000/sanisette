package com.maher.sanisettes.domain

import androidx.paging.PagingData
import com.google.android.gms.maps.model.LatLng
import com.maher.sanisettes.domain.model.Sanisette
import kotlinx.coroutines.flow.Flow

interface SanisetteRepository {

    fun getSanisettes(): Flow<PagingData<Sanisette>>

    fun getSanisettesByDistrict(district: String): Flow<PagingData<Sanisette>>

    fun getSanisettesByLatLng(latLng: LatLng): Flow<PagingData<Sanisette>>

}