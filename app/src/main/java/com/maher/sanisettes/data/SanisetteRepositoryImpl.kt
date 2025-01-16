package com.maher.sanisettes.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.google.android.gms.maps.model.LatLng
import com.maher.sanisettes.core.coroutines.DispatcherProvider
import com.maher.sanisettes.data.network.AllSanisettePagingSource
import com.maher.sanisettes.data.network.SanisetteApi
import com.maher.sanisettes.data.network.SanisettesByDistrictPagingSource
import com.maher.sanisettes.data.network.SanisettesByLatLingPagingSource
import com.maher.sanisettes.data.network.model.toDomainModel
import com.maher.sanisettes.domain.SanisetteRepository
import com.maher.sanisettes.domain.model.Sanisette
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SanisetteRepositoryImpl(
    private val sanisetteApi: SanisetteApi,
    private val dispatcher: CoroutineDispatcher = DispatcherProvider.io
) : SanisetteRepository {

    override fun getSanisettes(): Flow<PagingData<Sanisette>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            AllSanisettePagingSource(sanisetteApi)
        }.flow.map {
            it.map { sanisetteNetwork ->
                println("Sanisette = $it")
                sanisetteNetwork.toDomainModel()
            }
        }.flowOn(dispatcher)

    override fun getSanisettesByDistrict(district: String): Flow<PagingData<Sanisette>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            SanisettesByDistrictPagingSource(sanisetteApi = sanisetteApi, district = district)
        }.flow.map {
            it.map { sanisetteNetwork ->
                println("Sanisettes by district = $it")
                sanisetteNetwork.toDomainModel()
            }
        }.flowOn(dispatcher)

    override fun getSanisettesByLatLng(latLng: LatLng): Flow<PagingData<Sanisette>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            SanisettesByLatLingPagingSource(sanisetteApi = sanisetteApi, latLng = latLng)
        }.flow.map {
            it.map { sanisetteNetwork ->
                println("Sanisettes by LatLng = $it")
                sanisetteNetwork.toDomainModel()
            }
        }.flowOn(dispatcher)
}