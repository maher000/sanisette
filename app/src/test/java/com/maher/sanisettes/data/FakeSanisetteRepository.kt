package com.maher.sanisettes.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import com.google.android.gms.maps.model.LatLng
import com.maher.sanisettes.domain.SanisetteRepository
import com.maher.sanisettes.domain.model.Sanisette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSanisetteRepository : SanisetteRepository {
    override fun getSanisettes(): Flow<PagingData<Sanisette>> {
        val pagingSourceFactory = SANIETTES.asPagingSourceFactory()
        return Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = false
            )
        ) {
            pagingSourceFactory()
        }.flow
    }

    override fun getSanisettesByDistrict(district: String): Flow<PagingData<Sanisette>> {
        val pagingSourceFactory = SANIETTES_BY_DISTRICT.asPagingSourceFactory()
        return Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = false
            )
        ) {
            pagingSourceFactory()
        }.flow
    }

    override fun getSanisettesByLatLng(latLng: LatLng): Flow<PagingData<Sanisette>> {
        return flowOf(PagingData.from(SANIETTES_BY_LATLNG))
    }

    companion object {
        val SANIETTES = listOf(
            Sanisette(
                address = "Address 1",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 1",
                openHours = "Open hours 1",
                source = "Source 1"
            ),
            Sanisette(
                address = "Address 2",
                district = "District 2",
                latitude = 3.0,
                longitude = 4.0,
                manager = "Manager 2",
                openHours = "Open hours 2",
                source = "Source 2"
            ),
            Sanisette(
                address = "Address 3",
                district = "District 3",
                latitude = 5.0,
                longitude = 6.0,
                manager = "Manager 3",
                openHours = "Open hours 3",
                source = "Source 3"
            ),
            Sanisette(
                address = "Address 4",
                district = "District 4",
                latitude = 7.0,
                longitude = 8.0,
                manager = "Manager 4",
                openHours = "Open hours 4",
                source = "Source 4"
            ),
            Sanisette(
                address = "Address 5",
                district = "District 5",
                latitude = 9.0,
                longitude = 10.0,
                manager = "Manager 5",
                openHours = "Open hours 5",
                source = "Source 5"
            ),
            Sanisette(
                address = "Address 6",
                district = "District 6",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 6",
                openHours = "Open hours 6",
                source = "Source 6"
            ),
        )
        val SANIETTES_BY_DISTRICT = listOf(
            Sanisette(
                address = "Address 1",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 1",
                openHours = "Open hours 1",
                source = "Source 1"
            ),
            Sanisette(
                address = "Address 2",
                district = "District 1",
                latitude = 3.0,
                longitude = 4.0,
                manager = "Manager 2",
                openHours = "Open hours 2",
                source = "Source 2"
            ),
            Sanisette(
                address = "Address 3",
                district = "District 1",
                latitude = 5.0,
                longitude = 6.0,
                manager = "Manager 3",
                openHours = "Open hours 3",
                source = "Source 3"
            ),
            Sanisette(
                address = "Address 4",
                district = "District 1",
                latitude = 7.0,
                longitude = 8.0,
                manager = "Manager 4",
                openHours = "Open hours 4",
                source = "Source 4"
            ),
            Sanisette(
                address = "Address 5",
                district = "District 1",
                latitude = 9.0,
                longitude = 10.0,
                manager = "Manager 5",
                openHours = "Open hours 5",
                source = "Source 5"
            ),
            Sanisette(
                address = "Address 6",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 6",
                openHours = "Open hours 6",
                source = "Source 6"
            ),
        )
        val SANIETTES_BY_LATLNG = listOf(
            Sanisette(
                address = "Address 1",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 1",
                openHours = "Open hours 1",
                source = "Source 1"
            ),
            Sanisette(
                address = "Address 2",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 2",
                openHours = "Open hours 2",
                source = "Source 2"
            ),
            Sanisette(
                address = "Address 3",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 3",
                openHours = "Open hours 3",
                source = "Source 3"
            ),
            Sanisette(
                address = "Address 4",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 4",
                openHours = "Open hours 4",
                source = "Source 4"
            ),
            Sanisette(
                address = "Address 5",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 5",
                openHours = "Open hours 5",
                source = "Source 5"
            ),
            Sanisette(
                address = "Address 6",
                district = "District 1",
                latitude = 1.0,
                longitude = 2.0,
                manager = "Manager 6",
                openHours = "Open hours 6",
                source = "Source 6"
            )
        )
    }
}


